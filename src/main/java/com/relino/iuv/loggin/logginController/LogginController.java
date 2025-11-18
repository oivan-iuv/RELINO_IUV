package com.relino.iuv.loggin.logginController;

import com.relino.iuv.controllers.ReinicioPassword;
import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.entities.Contrasenia;
import com.relino.iuv.entities.Usuarios;
//import com.relino.relinov2.entities.UsuarioUnidadAdmin;
import com.relino.iuv.utils.SessionUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author Ivan
 */
@ManagedBean(name = "logginController", eager = true)
//El ManagedBean vivira durante la sesion del usuario es decir unicamente mientras este logueado  
@SessionScoped
public class LogginController extends SessionUsuario implements Serializable {

    private Usuarios loguedUser;
    private Contrasenia currentContrasenia;
    private String username = null;
    private String password = null;
    private String passActual = null;
    private String mensajeCambioPasswd = null;
    private String nuevoPasswdUser = null;
    private String confirmPasswdUser = null;
    private Integer intentosFallidos = 0;
    private Integer diasPorCaducar = 0;
    private boolean omitirCambioPass = false;
    private boolean botonOmitirPass = true;
    private boolean activaInicio;
    private boolean campoUsuario;
    //Enterprise JavaBeans
    @EJB
    private com.relino.iuv.facades.UsuariosFacade ejbUsuariosFacade;
    @EJB
    private com.relino.iuv.facades.UsuarioRolFacade ejbUsuarioRolFacade;
    @EJB
    private com.relino.iuv.facades.EstatusUsuarioFacade ejbEstatusUsuarioFacade;
  
    @PostConstruct
    public void initUsuario() {
        username = null;
        activaInicio = true;
        campoUsuario = false;
    }
    public void guardaAspirante() {
        
        System.out.println("Entra a Guardar Aspirante");
    }
    public void nuevoLogin() {
        System.out.println("usuario logeando: " + username + " con passwd: " + password);
        validar();
        System.out.println("termino de validar: ");
        if (!this.activaInicio && this.campoUsuario) {
            System.out.println("inicia a validar el passwd activaInicio: " + activaInicio + " campoUsuario: " + campoUsuario);
            validarPassword();
        }
    }

    /**
     * Método para validar si existe el nombre de usuario en la base de datos
     */
    public void validar() {
        FacesMessage facesMsg = null;
        //SE BUSCA EN LA BD SI EXISTE EL NOMBRE DE USUARIO PROPORCIONADO POR EL USUARIO
        try {
            this.loguedUser = this.ejbUsuariosFacade.findByUsuarios(this.username);
            if (this.loguedUser == null) {
                facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "El nombre de usuario no existe, intente con un nombre de usuario válido");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                this.username = "";
                this.activaInicio = false;
            } else {
                campoUsuario = true;
                // SI EXISTE EL USUARO, SE VALIDA EL ESTATUS
                System.out.println("Estatus del usuario: " + loguedUser.getEstatus());
                switch (this.loguedUser.getEstatus()) {
                    case 4: //PRIMER INGRESO                    
                        botonOmitirPass = true;
                        setAttribute("_USUARIO_SESSION_", this.loguedUser);
                        setAttribute("UA_SESSION", this.loguedUser.getIdCorporacion());
                        setAttribute("DEP_SESSION", this.loguedUser.getIdDependencia());
                        setAttribute("MUNI_SESSION", this.loguedUser.getIdMunicipio());
                  //    RequestContext.getCurrentInstance().execute("PF('dlgCambiaContrasenia').show();");
                        PrimeFaces.current().executeScript("PF('dlgCambiaContrasenia').show();");
                        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Cambio de contraseña de primer ingreso");
                        FacesContext.getCurrentInstance().addMessage("msjPass", facesMsg);
                        break;
                    case 3: // BLOQUEADO
                        // Validamos el tiempo que a transcurrido para saber si ya se puede desbloquear
                        Date sysdate = new Date();
                        Date ultimaAcitividad = this.loguedUser.getFechaBloqueo(); // se obtiene la fecha del ultimo bloqueo
                        long ONE_MINUTE_IN_MILLIS = 60000;
                        long t = ultimaAcitividad.getTime();
                        Date afterAddingMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
                        if (afterAddingMins.before(sysdate)) {
                            //Se activa nuevamente el usuario
                            this.loguedUser.setEstatus(1);
                            this.loguedUser.setFechaBloqueo(null);
                            ejbUsuariosFacade.edit(this.loguedUser);
                            activaInicio = false;
                        } else {
                            facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                    "El usuario está bloqueado, favor de intentar después de 10 minutos");
                            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                            this.username = "";
                        }
                        break;
                    case 2: // INACTIVO
                        // Se guarda en Bitácora el intento de Acceso al Aplicativo
//                        bitacoraController.SendBitacoras(this.loguedUser, "login", 1, "cuenta de usuario bloqueada por inactividad de más de 6 meses", "usuarios","","");
                        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                "La cuenta del usuario ha sido bloqueada por inactividad de más de 6 meses. "
                                + "Favor de comunicarse a mesa de servicio. Tel. 722 275 8200 y 722 275 8300, extensión 12345 para solicitad que reactiven su cuenta.");
                        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                        break;
                    case 0: // BAJA
                        // Se guarda en Bitácora el intento de Acceso
                        //  bitacoraController.SendBitacoras(this.loguedUser, "login",1, "intento de acceso al sistema pero la cuenta ha sido dada de baja por instruccion", "usuarios","","");
                        facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                "Lo sentimos, su cuenta de usuario se encuentra dada de baja, para mayor información "
                                + "favor de comunicarse a mesa de servicio. Tel. 722 275 8200 y 722 275 8300, extensión 12345.");
                        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                        break;
                    case 1: // ACTIVO
                        activaInicio = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception x) {
            this.activaInicio = true;
            this.campoUsuario = false;
            this.username = "";
            this.password = "";
            JsfUtil.addErrorMessage("Ocurrió un error al validar el usuario, favor de intentar nuevamente. Si el problema persiste comuníquese a mesa de servicio. Tel. 722 275 8200 y 722 2758 300, extensión 12345.");
        }
    }

    public void validarPassword() {
        boolean continuar = true;
        FacesMessage facesMsg;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            String passwordEncryp = JsfUtil.passToSHA(this.password);
            System.out.println("passwordEncriptado: " + passwordEncryp);
            if (this.loguedUser == null || this.loguedUser.getContraseniaList().isEmpty()) {
                this.loguedUser = this.ejbUsuariosFacade.findByUsuarios(this.username);
            }

            if (this.loguedUser.getPass().equals(passwordEncryp)) {
                // Valida la caducidad de la contraseña activa
                switch (ReinicioPassword.caducidadPassword(this.loguedUser.getFecModif())) {
                    case 1:
                        setAttribute("_USUARIO_SESSION_", this.loguedUser);
                        setAttribute("UA_SESSION", this.loguedUser.getIdCorporacion());
                        setAttribute("DEP_SESSION", this.loguedUser.getIdDependencia());
                        setAttribute("MUNI_SESSION", this.loguedUser.getIdMunicipio());
                        //mensajeCambioPass = "CAMBIO DE CONTRASEÑA POR CADUCIDAD";
                     //   RequestContext.getCurrentInstance().execute("PF('dlgCambiaContrasenia').show();");
                        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña ha expirado, favor de renovarla para tener acceso");
                        context.addMessage("msjPass", facesMsg);
                        botonOmitirPass = true;
                        continuar = false;
                        break;
                    case 2:
                        botonOmitirPass = false;
                        // Mandamos a llamar el cuadro de dialogo para cambio de contraseña
                      //  RequestContext.getCurrentInstance().execute("PF('dlgCambiaContrasenia').show();");
                        PrimeFaces.current().executeScript("PF('dlgCambiaContrasenia').show();");
                        facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "La contraseña está a ".concat(String.valueOf(diasPorCaducar)).concat(" días para caducar. ".concat("Puede continuar dando clic en el botón Omitir y actualizarla en otro momento.")));
                        context.addMessage("msjPass", facesMsg);
                        continuar = false;
                        break;
                    default:
                        break;
                }
                if (continuar) {
                    iniciarSession();
                }
            } else {
                intentosFallidos++;
                switch (intentosFallidos) {
                    case 2:
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "La contraseña es incorrecta"));
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
                                "Lleva dos intentos fallidos. Por seguridad, al tercer intento fallido, su cuenta quedará bloqueada durante 10 minutos."));
                        break;
                    case 3:
                        this.loguedUser.setFechaBloqueo(new Date());
                        this.loguedUser.setEstatus(3);
                        ejbUsuariosFacade.edit(this.loguedUser);
                        // bitacoraController.SendBitacoras(this.loguedUser,"login",1, "Bloqueo cuenta por intentos fallidos","cuenta de usuario bloqueada por exceder intentos de acceso", "usuarios","");
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error de acceso",
                                "Su cuenta de usuario ha sido bloqueada por seguridad, debido a varios intentos fallidos, "
                                + "favor de intentarlo dentro de 10 minutos."));
                        intentosFallidos = 0;
                        limpiarUsuario();
                        request.getSession().removeAttribute(_USUARIO_SESSION_);
                        request.getSession().removeAttribute(UA_SESSION);
                        request.getSession().removeAttribute(DEP_SESSION);
                        request.getSession().removeAttribute(MUNI_SESSION);
                        request.getSession().invalidate();
                        break;
                    default:
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "La contraseña es incorrecta"));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en validarPassword(): " + e.getMessage());
            request.getSession().removeAttribute("userSession");
            request.getSession().removeAttribute(_USUARIO_SESSION_);
            request.getSession().removeAttribute(UA_SESSION);
            request.getSession().removeAttribute(DEP_SESSION);
            request.getSession().removeAttribute(MUNI_SESSION);           
            request.getSession().invalidate();
            redirectLogout();
            JsfUtil.addErrorMessage("Error al validar al usuario y contraseña");
        }
    }

//    private HttpSession getSession() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//
//        return (HttpSession) request.getSession();
//    }
//    
//    private Object getAttribute(String attribute) {
//
//        return getSession().getAttribute(attribute);
//    }
//
//    private void setAttribute(String nameAttribute, Object value) {
//
//        getSession().setAttribute(nameAttribute, value);
//    }
//
//    public Usuarios getUsuarioSession() {
//        return (Usuarios) getSession().getAttribute("__USUARIO_SESSION__");
//    }
//
//    public void setUsuarioSession(Usuarios usuario) {
//        getSession().setAttribute("_USUARIO_SESSION_", usuario);
//    }
    private void iniciarSession() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("loguedUser: " + loguedUser + " ua: " + loguedUser.getIdCorporacion() + " dEP: " + loguedUser.getIdDependencia());
            setAttribute("_USUARIO_SESSION_", this.loguedUser);
            setAttribute("UA_SESSION", this.loguedUser.getIdCorporacion());
            setAttribute("DEP_SESSION", this.loguedUser.getIdDependencia());
            setAttribute("MUNI_SESSION", this.loguedUser.getIdMunicipio());
            String pagina = context.getExternalContext().getRequestContextPath().concat("/inicio.xhtml");
            context.getExternalContext().redirect(pagina);
            // bitacoraController.SendBitacoras(this.loguedUser, "LOGIN", 1, "login", "EL USUARIO INICIO SESION E INGRESO SISTEMA", "", "");
        } catch (IOException io) {
            System.out.println("Error en iniciarSession(): " + io.getMessage());
            JsfUtil.addErrorMessage("Error! Exception occured");
        } catch (Exception ex) {
            System.out.println("Error en iniciarSession(): " + ex.getMessage());
            JsfUtil.addErrorMessage("Error! Exception occured");
        }
    }

    //Método para limpiar el panel de usuario y contraseña
    public void limpiarUsuario() {
        loguedUser = new Usuarios();
        username = "";
        password = "";
    }

    // Método que permite al usuario salir a la página de Logueo (login).
    public void logout(int num) throws IOException {
        // Se guarda en Bitácora la salida del sistema
        //usuarioSession, modulo, IdAccionNueva, accion, descripcion, registroModificado, registroAnterior
        //  bitacoraController.SendBitacoras(loguedUser, "LOGOUT", 2, "logout", "EL USUARIO CERRO SESION Y SALIO DEL SISTEMA", "", "");

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            limpiarSesion();
            ejbUsuariosFacade.remueveCacheUsuario(loguedUser);

            String folderPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(folderPath);
        } catch (IOException e) { // fin del try
            //System.out.println("Hubo error el cerrar sesión: " + e.getMessage());
        }
    }  // Fin del Método logout(int num)

    // Se limpian los valores de la sesión
    private void limpiarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        context.getExternalContext().getSessionMap().clear();
        request.removeAttribute("userName");
        request.removeAttribute("contOportunidad");
        request.removeAttribute("userSession");
        request.removeAttribute("idCorporacion");
        request.removeAttribute("idDependencia");
        request.removeAttribute("rol");
        request.removeAttribute("password");
        System.out.println("request.removeAttributeuserName: "+getUsuarioSession());

        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("contOportunidad");
        request.getSession().removeAttribute("userSession");
        request.getSession().removeAttribute("idCorporacion");
        request.getSession().removeAttribute("idDependencia");
        request.getSession().removeAttribute("rol");
        request.getSession().removeAttribute("password");
        System.out.println("request.removeAttributeuserName:2 "+getUsuarioSession());

        request.getSession().removeAttribute(_USUARIO_SESSION_);
        request.getSession().removeAttribute(UA_SESSION);
        request.getSession().removeAttribute(DEP_SESSION);
        request.getSession().removeAttribute(MUNI_SESSION);
        request.getSession().removeAttribute(rol);
        System.out.println("request.removeAttributeuserName: 3"+getUsuarioSession());

    }

    public void bloqueaUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        loguedUser = ejbUsuariosFacade.findByUsuarios(username);

        if (loguedUser != null) {
            loguedUser.setFechaBloqueo(new Date());
            loguedUser.setEstatus(3);
            ejbUsuariosFacade.edit(loguedUser);

            //usuarioSession, modulo, IdAccionNueva, accion, descripcion, registroModificado, registroAnterior
            // bitacoraController.SendBitacoras(this.loguedUser, "LOGIN", 1, "login", "LA CUENTA DEL USUARIO HA SIDO BLOQUEADA POR EXCEDER EL LIMITE DE INTENTOS PERMITIDOS", "USUARIO - ID ESTATUS USUARIO = 3", "");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error de acceso",
                    "El usuario ha sido bloqueado por seguridad, debido a varios intentos fallidos, "
                    + "favor de volver a intentarlo dentro de 10 minutos"));

        } else {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error de acceso",
                    "El nombre de usuario no existe, intenta con otro nombre de usuario válido"));
        }
    }

    // Método para que se Cancele el cuadro de dialogo de cambio de contraseña
    public void omitirCambioPasswd() {
        //login();
        iniciarSession();
        //RequestContext.getCurrentInstance().execute("PF('wid_cambioPass').hide()");
        PrimeFaces.current().executeScript("PF('wid_cambioPass').hide();");
    }

    //Método que permite verificar que el usuario se encuentre logueado
    public void confirmarSession() {
        loguedUser = getUsuarioSession();
        System.out.println("Ingresó a confirmar sesion");
        //this.loguedUser = getUsuariosSession();
        if (this.loguedUser == null) {
            try {
                redirectLogout();
            } catch (javax.faces.application.ViewExpiredException vx) {
                System.out.println("No se reestablecio la vista: " + vx.getViewId());
            } catch (Exception LocalException) {
                System.out.println("Error al reestablecer la vista: " + LocalException.getMessage());
            }
        }

    }

    //Metodo que permite redireccionar a loguin unicamente  cuando caducar la sesion de usuario
    public void redirectLogout() {
        try {
            String folderPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            limpiarSesion();
            FacesContext.getCurrentInstance().getExternalContext().redirect(folderPath);
//            System.out.println("folderPath: " + folderPath);
        } catch (javax.faces.application.ViewExpiredException vx) {
            System.out.println("No se reestablecio la vista: " + vx.getViewId());
        } catch (Exception ex) {
            System.out.println("No se reestablecio la vista: " + ex.getMessage());
        }
    }

    /*
     *  Fin de la programación Personalizada
     */
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public String getMensajeCambioPasswd() {
        return mensajeCambioPasswd;
    }

    public void setMensajeCambioPasswd(String mensajeCambioPasswd) {
        this.mensajeCambioPasswd = mensajeCambioPasswd;
    }

    public String getNuevoPasswdUser() {
        return nuevoPasswdUser;
    }

    public void setNuevoPasswdUser(String nuevoPasswdUser) {
        this.nuevoPasswdUser = nuevoPasswdUser;
    }

    public String getConfirmPasswdUser() {
        return confirmPasswdUser;
    }

    public void setConfirmPasswdUser(String confirmPasswdUser) {
        this.confirmPasswdUser = confirmPasswdUser;
    }

    public Contrasenia getCurrentContrasenia() {
        return currentContrasenia;
    }

    public void setCurrentContrasenia(Contrasenia currentContrasenia) {
        this.currentContrasenia = currentContrasenia;
    }

    public boolean getBotonOmitirPass() {
        return botonOmitirPass;
    }

    public void setBotonOmitirPass(boolean botonOmitirPass) {
        this.botonOmitirPass = botonOmitirPass;
    }

//    public BitacoraController getBitacoraController() {
//        return bitacoraController;
//    }
//
//    public void setBitacoraController(BitacoraController bitacoraController) {
//        this.bitacoraController = bitacoraController;
//    }
    public Usuarios getLoguedUser() {
        return loguedUser;
    }

    public void setLoguedUser(Usuarios loguedUser) {
        this.loguedUser = loguedUser;
    }

    public boolean isActivaInicio() {
        return activaInicio;
    }

    public void setActivaInicio(boolean activaInicio) {
        this.activaInicio = activaInicio;
    }

    public boolean isCampoUsuario() {
        return campoUsuario;
    }

    public void setCampoUsuario(boolean campoUsuario) {
        this.campoUsuario = campoUsuario;
    }

}
