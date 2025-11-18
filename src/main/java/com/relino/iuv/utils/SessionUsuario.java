/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.utils;

import com.relino.iuv.entities.CatCorporacion;
import com.relino.iuv.entities.CatDependencia;
import com.relino.iuv.entities.CatMunicipio;
import com.relino.iuv.entities.Usuarios;
import com.relino.iuv.entities.UsuarioRol;
//import com.relino.relinov2.entities.UsuarioUnidadAdmin;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ivan
 */
public class SessionUsuario implements Serializable {

    FacesContext facesContext;
    HttpServletRequest request;
    HttpSession session;

    public static final String _USUARIO_SESSION_ = "userSession";
    public static final String contOportunidad = "contOportunidad";
    public static final String pass = "password";
    public static final String userName = "username";
    public static final String rol = "rol";
    public static final String UA_SESSION = "idCorporacion";
    public static final String DEP_SESSION = "idDependencia";
    public static final String MUNI_SESSION = "idMunicipio";

    //Construcción de la sesion para almacenar los valores
    public SessionUsuario() {

    }

    @PostConstruct
    private void init() {
        getSession();
    }

    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return (HttpSession) request.getSession();
    }

    public HttpServletRequest getRequestContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request;
    }

    public Object getAttribute(String attribute) {

        return getSession().getAttribute(attribute);
    }

    public void setAttribute(String nameAttribute, Object value) {

        getSession().setAttribute(nameAttribute, value);
    }

    public void removeAttributes(String nameAttribute) {
        request.removeAttribute(nameAttribute);
    }

    public Usuarios getUsuarioSession() {
        return (Usuarios) getSession().getAttribute("_USUARIO_SESSION_");
    }

    public void setUsuarioSession(Usuarios usuario) {
        getSession().setAttribute("_USUARIO_SESSION_", usuario);
    }

// Ponemos en Sesión la unidadAdministrativa o Corporacion        
    public CatCorporacion getUaSession() {
        return (CatCorporacion) getSession().getAttribute("UA_SESSION");
    }

    public void setUaSession(CatCorporacion corporacion) {
        getSession().setAttribute("UA_SESSION", corporacion);
    }

    // Ponemos en Sesión la Dependencia    
    public CatDependencia getDepSession() {
        return (CatDependencia) getSession().getAttribute("DEP_SESSION");
    }

    public void setDepSession(CatDependencia dependencia) {
        getSession().setAttribute("DEP_SESSION", dependencia);
    }

    public CatMunicipio getMuniSession() {
        return (CatMunicipio) getSession().getAttribute("MUNI_SESSION");
    }

    public void setMuniSession(CatMunicipio municipio) {
        getSession().setAttribute("MUNI_SESSION", municipio);
    }

    // Ponemos en Sesión el conteo de veces fallidas para ingresar al aplicativo.
    public Integer getContOportunidad() {
        return (Integer) session.getAttribute(contOportunidad);
    }

    public void setContOportunidad(Integer cont) {
        session.setAttribute(contOportunidad, cont);
    }

    // Ponemos en Sesión el password
    public String getPassSession() {
        return (String) session.getAttribute(pass);
    }

    public void setPassSession(String dato) {
        session.setAttribute(pass, dato);
    }

    // Ponemos en Sesión el UserName
    public String getUsern() {
        return (String) session.getAttribute(userName);
    }

    public void setUsern(String dato) {
        session.setAttribute(userName, dato);
    }

    // Ponemos en Sesión el tipo de rol que tiene el Usuario
    public UsuarioRol getRol() {
        return (UsuarioRol) session.getAttribute(rol);
    }

    public void setRol(UsuarioRol dato) {
        session.setAttribute(rol, dato);
    }

    public String GET_REMOTE_IP_SESSION() {
        return request.getRemoteAddr();
    }

    public String GET_REMOTE_HOST_SESSION() {
        return request.getRemoteHost();
    }

}
