package com.relino.iuv.controllers;

import com.relino.iuv.entities.CatMunicipioColoniaCp;
import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.controllers.util.PaginationHelper;
import com.relino.iuv.facades.CatMunicipioColoniaCpFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("catMunicipioColoniaCpController")
@SessionScoped
public class CatMunicipioColoniaCpController implements Serializable {

    private CatMunicipioColoniaCp current;
    private DataModel items = null;
    @EJB
    private com.relino.iuv.facades.CatMunicipioColoniaCpFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CatMunicipioColoniaCpController() {
    }

    public CatMunicipioColoniaCp getSelected() {
        if (current == null) {
            current = new CatMunicipioColoniaCp();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CatMunicipioColoniaCpFacade getFacade() {
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
        current = (CatMunicipioColoniaCp) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CatMunicipioColoniaCp();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioColoniaCpCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CatMunicipioColoniaCp) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioColoniaCpUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CatMunicipioColoniaCp) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioColoniaCpDeleted"));
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

    public CatMunicipioColoniaCp getCatMunicipioColoniaCp(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = CatMunicipioColoniaCp.class)
    public static class CatMunicipioColoniaCpControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatMunicipioColoniaCpController controller = (CatMunicipioColoniaCpController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catMunicipioColoniaCpController");
            return controller.getCatMunicipioColoniaCp(getKey(value));
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
            if (object instanceof CatMunicipioColoniaCp) {
                CatMunicipioColoniaCp o = (CatMunicipioColoniaCp) object;
                return getStringKey(o.getIdCatMunicipioColoniaCp());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CatMunicipioColoniaCp.class.getName());
            }
        }

    }

}
