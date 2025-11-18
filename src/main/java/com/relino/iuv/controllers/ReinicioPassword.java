/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.controllers;

import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.entities.Contrasenia;
import com.relino.iuv.entities.Usuarios;
import com.relino.iuv.loggin.logginController.LogginController;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import org.primefaces.context.RequestContext;
import com.relino.iuv.utils.Correo;
import com.relino.iuv.utils.SessionUsuario;
import org.primefaces.PrimeFaces;

/**
 *
 * @author JAN
 */
@ManagedBean(name = "passwordController")
@ViewScoped
public class ReinicioPassword extends SessionUsuario implements Serializable {

    private boolean cambioExitoso;
    private boolean procesoMenu;
    private short opcion;
    private String password1;
    private String password2;
    private Usuarios usuario;
    private String url;
    @EJB
    private com.relino.iuv.facades.UsuariosFacade ejbFacadeUsuario;
    @EJB
    private com.relino.iuv.facades.ContraseniaFacade ejbContraseniaFacade;
    private FacesMessage mensaje;
    @ManagedProperty(value = "#{logginController}")
    private LogginController logginController = new LogginController();
    @ManagedProperty(value = "#{bitacoraController}")
    private BitacoraController bitacoraController;
    @EJB
    private com.relino.iuv.facades.EstatusUsuarioFacade ejbEstatusUsuarioFacade;
    private static int diasPorCaducar = 0;

    public ReinicioPassword() {
        this.usuario = new Usuarios();
        this.cambioExitoso = false;
    }

    @PostConstruct
    private void init() {
        opcion = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opcion") == null ? new Short("0") : Short.parseShort(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opcion")));
        procesoMenu = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu") == null ? false : (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu").equals("1"));
        url = "https://" + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName() + ":"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort()
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        //+FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath() + "?" ;
        //System.out.println("URL: " + url);
    

    
        switch (opcion) {
            case 1://OPCION=1 ES CUANDO VIENE DEL CORREO ELECTRÓNICO
                this.usuario.setIdUsuario((FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario") == null ? new Integer("0") : Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"))));
                if (this.usuario.getIdUsuario() > 0) {
                    this.usuario = ejbFacadeUsuario.findByIdUsuario(this.usuario.getIdUsuario());
                }
                break;
            case 2://OPCION=2 ES CUANDO VIENE DEL MENU
                //(Usuarios) getAttribute("_USUARIO_SESSION_")
                        
                if (getUsuarioSession() != null && getUsuarioSession().getIdUsuario() != null) {
                    this.usuario = getUsuarioSession();
                } else {
                    this.usuario.setIdUsuario((FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario") == null ? new Integer("0") : Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"))));
                    if (this.usuario.getIdUsuario() > 0) {
                        this.usuario = ejbFacadeUsuario.findByIdUsuario(this.usuario.getIdUsuario());
                    }
                }
                break;
            default:
                break;
        }
    }

    public void actualizarPassword(int opc) {
        String mensajeBitacora = "";
        if (password1.equals(password2)) {
            String passwordEncriptado = JsfUtil.passToSHA(password1);
            if (usuario == null || usuario.getIdUsuario() == null) {
                usuario = getUsuarioSession();
            }

            if (!ejbContraseniaFacade.findByUserAndPassBoolean(usuario.getIdUsuario(), passwordEncriptado)) {
                if ((password1.length() >= 8) && (password1.length() <= 12)) {
                    if (password1.matches(".*[A-Z].*")) {
                        if (password1.matches(".*[a-z].*")) {
                            if (password1.matches(".*[0-9].*")) {
                                if (password1.matches(".*[#$%&_-|;:,+/].*")) {
                                    Contrasenia passActiva = this.ejbContraseniaFacade.findByPassActiva(this.usuario.getIdUsuario());
                                    if (passActiva != null && passActiva.getIdContrasenia() != null) {
                                        passActiva.setIdUsuarios(usuario);
                                        passActiva.setActiva(new Short("0"));
                                        this.ejbContraseniaFacade.edit(passActiva);
                                    }
                                    passActiva = new Contrasenia();
                                    passActiva.setActiva(new Short("1"));
                                    passActiva.setPassword(passwordEncriptado);
                                    passActiva.setFechaCambio(new Date());
                                    passActiva.setIdUsuarios(usuario);
                                    this.ejbContraseniaFacade.create(passActiva);

                                    if (usuario.getIdEstatusUsuario().getIdEstatusUsuario() != 1) {
                                        usuario.setIdEstatusUsuario(ejbEstatusUsuarioFacade.find((Integer) 1));
                                        this.ejbFacadeUsuario.edit(this.usuario);
                                    }
                                    if (opc == 1) {
                                 //     RequestContext.getCurrentInstance().addCallbackParam("cerrarDlg", true);
                                        PrimeFaces.current().ajax().addCallbackParam("cerrarDlg", true);
                                        mensajeBitacora = "EL USUARIO MODIFICO SU CONTRASEÑA DESDE LINK CORREO";
                                        redirectLogin();
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "La contraseña ha sido modificada correctamente."));
                                    } else {
                                        password1 = "";
                                        password2 = "";
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "La contraseña ha sido modificada correctamente."));
                                        mensajeBitacora = "EL USUARIO MODIFICO SU CONTRASEÑA DESDE MENU";
                                        //Se actualizan los valores del objeto usuario en sesion
                                        this.usuario = ejbFacadeUsuario.findByIdUsuario(this.usuario.getIdUsuario());
                                        setUsuarioSession(this.usuario);
                                    }
                                    //bitacoraController.SendBitacoras(usuario, "LOGIN",1,"insert", mensajeBitacora + "id_usuario: " + this.usuario.getIdUsuario(),"","");
                                    cambioExitoso = true;
                                } else {
                                    FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña debe contener por lo menos caracter especial."));
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña debe contener por lo menos un número."));
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña debe contener por lo menos una letra minúscula."));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña debe contener por lo menos una letra mayúscula."));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña debe contener entre 8 y 12 caracteres."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraeña ha sido usada anteriormente, por favor ingrese una que nunca haya registrado en el sistema."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("msjPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden."));
        }
        this.password1 = "";
        this.password2 = "";
    }

    public void verificarUsuario() {
        if (this.usuario.getUsuario() != null && !this.usuario.getUsuario().equals("")) {
            usuario = ejbFacadeUsuario.findByUsuarios(this.usuario.getUsuario());
            if (usuario != null) {
                if (usuario.getIdEstatusUsuario().getIdEstatusUsuario().equals(1)) {
                    if (enviarCorreoConfirmacion()) {
                        mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se ha enviado un correo electrónico a " + usuario.getDatosUsuarioList().get(0).getEmail() + " para que siga las instrucciones y pueda realizar el cambio de la contraseña");
                        FacesContext.getCurrentInstance().addMessage("msjRecovery", mensaje);
                    } else {
                        mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al enviar el correo electrónico. Puede comunicarse con el encargado del sistema para solicitar que el cambio sea a travez del sistema.");
                        FacesContext.getCurrentInstance().addMessage("msjRecovery", mensaje);
                    }
                } else {
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "El usuario " + this.usuario.getUsuario() + " actualmente se encuentra inhabilitado. Favor de comunicarse con el  encargado del sistema.");
                    FacesContext.getCurrentInstance().addMessage("msjRecovery", mensaje);
                    this.usuario = new Usuarios();
                }
            } else {
                mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No existen registros para el usuario. Favor de verificar.");
                FacesContext.getCurrentInstance().addMessage("msjRecovery", mensaje);
                this.usuario = new Usuarios();
            }
        } else {
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Debe ingresar el usuario.");
            FacesContext.getCurrentInstance().addMessage("msjRecovery", mensaje);
        }
    }

    /*public void reinicarPassword() {
        String passwordHash = "";
        String mensajeBitacora = "";
        try {

            //validar.setPassSession(pass); //PassSession(password1);
            //validar.setUsern(this.usuario.getUsuario());
            logginController.setPassword(password1);
            logginController.setPassActual(password1);
            logginController.setNuevoPasswdUser(password1);
            logginController.setConfirmPasswdUser(password2);
            //validar.setPassSession(usuario.getPassword());
            logginController.setUsername(this.usuario.getUsuario());
            logginController.setPassword(this.usuario.getPassword());
            //validar.setUsuarioSession(this.usuario);
            logginController.setLoguedUser(this.usuario);
            if (logginController.verificaFormatoNuevoPassUser(opcion)) {
                if (!password1.equals(password2)) {
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La nueva contraseña que ingreso no coincide.");
                } else {
                    //this.usuario = ejbFacadeUsuario.findByIdUsuario(this.usuario.getIdUsuario());
                    passwordHash = JsfUtil.passToSHA(password1.trim());
                    this.usuario.setPassword(passwordHash);
                    this.usuario.setFechaModificacion(new Date());
                    this.usuario.setIdEstatusUsuario(new EstatusUsuario(1));
                    this.usuario.setPassword(JsfUtil.passToSHA(password1));
                    ejbFacadeUsuario.edit(usuario);

                    Contrasenia contrasenia = new Contrasenia();
                    contrasenia.setFechaCambio(new Date());
                    contrasenia.setIdUsuario(this.usuario);
                    contrasenia.setContrasenia(password1);
                    contrasenia.setPassword(passwordHash);
                    ejbContraseniaFacade.create(contrasenia);
                    if (procesoMenu) {
                        mensajeBitacora = "EL USUARIO MODIFICO SU CONTRASEÑA DESDE MENU";
                    } else {
                        mensajeBitacora = "EL USUARIO MODIFICO SU CONTRASEÑA DESDE LINK CORREO";
                    }

                    bitacoraController.SendBitacoras(this.usuario, "CONTRASENIA", 4, "modificar", mensajeBitacora, "PASSWORD MODIFICADO", "");
                    //RequestContext.getCurrentInstance().update(":login_Form:login_passwd");
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "La contraseña se actualizó correctamente");
                    FacesContext.getCurrentInstance().addMessage("messages", mensaje);
                    this.cambioExitoso = true;
                }
            }
        } catch (Exception ex) {
            this.usuario = new Usuario();
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al actualizar la contraseña. Para mayor información "
                    + "favor de comunicarse a mesa de servicio al teléfono (01722) 275-8200 y 275-8300, extensión 12345.");
            FacesContext.getCurrentInstance().addMessage("messages", mensaje);
        }
    }
     */
    private boolean enviarCorreoConfirmacion() {
        boolean enviado = false;
        String correo = this.bodyCorreo, link = url;
        if (!link.contains("localhost")) {
            link = link.replace(":" + FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort(), "");
        }
        correo = correo.replaceAll("#user#", this.usuario.getUsuario());
        correo = correo.replace("#fecha#", new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()));
        correo = correo.replace("#link#", "" + link + "/views/controlAcceso/recuperarContrasenia.xhtml?opcion=2&idUsuario=".concat(String.valueOf(this.usuario.getIdUsuario())));
        correo = correo.replace("*URL*", url);
        try {
            Correo enviarMail = new Correo();
            //enviado = enviarMail.enviarCorreo(this.usuario.getCorreoElectronico(), "",correo, "Reinicio de contraseña Sistema de Control de Gestión de Documentos");
            enviado = enviarMail.sendPostMail(this.usuario.getDatosUsuarioList().get(0).getEmail(), "Reinicio de contraseña Gestión de Documental", correo);
        } catch (Exception E) {
        }
        return enviado;
    }

    public void redirectLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().removeAttribute(_USUARIO_SESSION_);
            request.getSession().removeAttribute(rol);
            request.getSession().removeAttribute(UA_SESSION);
            request.getSession().removeAttribute(DEP_SESSION);
            System.out.println("request.removeAttributeuserName: 1 en reinicioPasswd"+getUsuarioSession());
            request.getSession().removeAttribute("loguedUser");
            System.out.println("request.removeAttributeuserName: 2 en reinicioPasswd"+getUsuarioSession());
            
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            String folderPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(folderPath);
        } catch (IOException ex) {
            this.usuario = new Usuarios();
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al redireccionar a la página de inicio.");
            FacesContext.getCurrentInstance().addMessage("messages", mensaje);
        }
    }

    public static int caducidadPassword(Date fechaModificacion) {
        diasPorCaducar = 0;
        Integer dias;
        dias = (int) ((new Date().getTime() - fechaModificacion.getTime()) / (1000 * 60 * 60 * 24));

        if (dias >= 180) {
            return 1;
        } else if (dias >= 165 && dias <= 179) {
            diasPorCaducar = 180 - dias;
            return 2;
        } else {
            return 0;
        }
    }

    public static int getDiasPorCaducar() {
        return diasPorCaducar;
    }

    public short getOpcion() {
        return opcion;
    }

    public void setOpcion(short opcion) {
        this.opcion = opcion;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isCambioExitoso() {
        return cambioExitoso;
    }

    public LogginController getLogginController() {
        return logginController;
    }

    public void setLogginController(LogginController logginController) {
        this.logginController = logginController;
    }

    public BitacoraController getBitacoraController() {
        return bitacoraController;
    }

    public void setBitacoraController(BitacoraController bitacoraController) {
        this.bitacoraController = bitacoraController;
    }

    public boolean isProcesoMenu() {
        return procesoMenu;
    }
    private final String bodyCorreo
            = "<?xml version='1.0' encoding='UTF-8'?>"
            + "<!DOCTYPE>"
            + "<html xmlns='http://www.w3.org/1999/xhtml'>"
            + "<head >"
            + "   <title>GESTIÓN DOCUMENTAL</title>"
            + "   <meta charset='UTF-8' />"
            + "   <link type='text/css' rel='stylesheet' href='*URL*/resources/css/blue_theme.css' /> "
            + "   <script type='text/javascript' src='*URL*/resources/languaje.js' language='javascript'></script>"
            + "</head>"
            + "<body id='plantillaGRAL' style='border: 0px; padding: 0px; '>"
            + "<table style='background-color:#f8f8f8; border-top:6px solid #1da6df; text-align:center; width:800px;'>"
            + "  <tr>"
            + "    <td  style='width:20%;'> </td>"
            + "    <td>"
            + "      <br/>"
            + "        <img src='*URL*/media/imagenes/correoEnc.PNG'/>"
            + "      <br/>"
            + "    </td>"
            + "    <td  style='width:20%;'> </td>"
            + "  </tr>"
            + "  <tr>"
            + "    <td  style='width:20%;'> </td>"
            + "      <td style='border:1px solid #999; box-shadow:#999; width:60%; padding:25px; margin:20px; text-align:center; font-family:Arial, Helvetica, sans-serif;color:#666;'>"
            + "        <strong><a>Recuperar Contraseña.</a></strong>"
            + "        <br/>"
            + "        <br/>"
            + "        Has solicitado el restablecimiento de contraseña"
            + "        para tu cuenta <b>#user#</b> del <strong><a>Sistema de Gestión Documental.</a></strong>"
            + "        <br />"
            + "        <br />"
            + "        Si no realizó esta solicitud, puede ignorar este correo electrónico de forma segura."
            + "        De lo contrario, haga clic en el enlace de abajo para completar el proceso."
            + "        <br />"
            + "        <br />"
            + "        Fecha y hora de operación: <b>#fecha#</b>"
            + "        <br />"
            + "        <a href='#link#'  title='De clic para cambiar la contraseña'>Actualizar contraseña</a>"
            + "      </td>"
            + "    <td  style='width:20%;'> </td>"
            + "  </tr>"
            + "  <tr>"
            + "    <td  style='width:20%;'> </td>"
            + "      <td>"
            + "        <br />"
            + "        <img src='*URL*/media/imagenes/logosCorreo.png'  />"
            + "        <br />"
            + "      </td>"
            + "      <td  style='width:20%;'> </td>"
            + "  </tr>"
            + "</table>"
            + "</body>"
            + "</html>";
}
