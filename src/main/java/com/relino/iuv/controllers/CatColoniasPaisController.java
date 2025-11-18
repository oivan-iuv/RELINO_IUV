package com.relino.iuv.controllers;

import com.relino.iuv.entities.CatColoniasPais;
import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.controllers.util.PaginationHelper;
import com.relino.iuv.entities.CatEstadosPais;
import com.relino.iuv.entities.CatMunicipioPais;
import com.relino.iuv.facades.CatColoniasPaisFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("catColoniasPaisController")
@SessionScoped
public class CatColoniasPaisController implements Serializable {

    private CatColoniasPais current;
    private DataModel items = null;
    @EJB
    private com.relino.iuv.facades.CatColoniasPaisFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<CatColoniasPais> coloniaListAjax = null;
    private List<CatColoniasPais> coloniaListAjaxDom = null;
    private List<CatColoniasPais> coloniaListAjaxRef = null;
    
    
    public CatColoniasPaisController() {
    }

    //**************************** BUSCA COLONIAS POR MUNICIPIO REFERENCIAS
    public void buscaColoniabyMuni(AjaxBehaviorEvent evn) {
        CatMunicipioPais objMuni = (CatMunicipioPais) ((UIOutput) evn.getSource()).getValue();
        Integer claveMunicipioPais = objMuni.getClaveMunicipioPais();
        Integer claveEstadoPais = objMuni.getIdEstadoPais().getClaveEstadoPais();
        this.coloniaListAjax = ejbFacade.findByClaveMunicipioPais(claveMunicipioPais, claveEstadoPais);
    }

    public SelectItem[] getItemsColoniaListAjxSelectManyCats() {
        return coloniaListAjax != null ? JsfUtil.getSelectItemsCats(this.coloniaListAjax, false) : null;
    }
    
    public void buscaColoniabyMuniDom(AjaxBehaviorEvent evn) {
        CatMunicipioPais objMuni = (CatMunicipioPais) ((UIOutput) evn.getSource()).getValue();
        Integer claveMunicipioPais = objMuni.getClaveMunicipioPais();
        Integer claveEstadoPais = objMuni.getIdEstadoPais().getClaveEstadoPais();
        this.coloniaListAjaxDom = ejbFacade.findByClaveMunicipioPais(claveMunicipioPais, claveEstadoPais);
    }

    public SelectItem[] getItemsColoniaListAjxSelectManyCatsDom() {
        return coloniaListAjaxDom != null ? JsfUtil.getSelectItemsCats(this.coloniaListAjaxDom, false) : null;
    }    
    
    public void buscaColoniabyMuniRef(AjaxBehaviorEvent evn) {
        CatMunicipioPais objMuni = (CatMunicipioPais) ((UIOutput) evn.getSource()).getValue();
        Integer claveMunicipioPais = objMuni.getClaveMunicipioPais();
        Integer claveEstadoPais = objMuni.getIdEstadoPais().getClaveEstadoPais();
        this.coloniaListAjaxRef = ejbFacade.findByClaveMunicipioPais(claveMunicipioPais, claveEstadoPais);
    }

    public SelectItem[] getItemsColoniaListAjxSelectManyCatsRef() {
        return coloniaListAjaxRef != null ? JsfUtil.getSelectItemsCats(this.coloniaListAjaxRef, false) : null;
    }        
    
    
    public CatColoniasPais getSelected() {
        if (current == null) {
            current = new CatColoniasPais();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CatColoniasPaisFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (CatColoniasPais) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CatColoniasPais();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatColoniasPaisCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CatColoniasPais) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatColoniasPaisUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CatColoniasPais) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatColoniasPaisDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public CatColoniasPais getCatColoniasPais(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = CatColoniasPais.class)
    public static class CatColoniasPaisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatColoniasPaisController controller = (CatColoniasPaisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catColoniasPaisController");
            return controller.getCatColoniasPais(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CatColoniasPais) {
                CatColoniasPais o = (CatColoniasPais) object;
                return getStringKey(o.getIdColoniaPais());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CatColoniasPais.class.getName());
            }
        }

    }

    public List<CatColoniasPais> getColoniaListAjaxDom() {
        return coloniaListAjaxDom;
    }

    public void setColoniaListAjaxDom(List<CatColoniasPais> coloniaListAjaxDom) {
        this.coloniaListAjaxDom = coloniaListAjaxDom;
    }

    public List<CatColoniasPais> getColoniaListAjaxRef() {
        return coloniaListAjaxRef;
    }

    public void setColoniaListAjaxRef(List<CatColoniasPais> coloniaListAjaxRef) {
        this.coloniaListAjaxRef = coloniaListAjaxRef;
    }

}
