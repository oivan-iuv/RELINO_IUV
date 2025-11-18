package com.relino.iuv.controllers;

import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.entities.Contrasenia;
import com.relino.iuv.facades.ContraseniaFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "contraseniaController")
@SessionScoped
public class ContraseniaController implements Serializable {

    private Contrasenia current;
    @EJB
    private com.relino.iuv.facades.ContraseniaFacade ejbFacade;

    public ContraseniaController() {
    }

    public Contrasenia getSelected() {
        if (current == null) {
            current = new Contrasenia();
        }
        return current;
    }

    private ContraseniaFacade getFacade() {
        return ejbFacade;
    }

    
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Mensajeria").getString("ContraseniaCreated"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Mensajeria").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Mensajeria").getString("ContraseniaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Mensajeria").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    @FacesConverter(forClass = Contrasenia.class)
    public static class ContraseniaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContraseniaController controller = (ContraseniaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contraseniaController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contrasenia) {
                Contrasenia o = (Contrasenia) object;
                return getStringKey(o.getIdContrasenia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Contrasenia.class.getName());
            }
        }
    }
}
