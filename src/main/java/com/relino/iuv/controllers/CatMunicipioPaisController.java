package com.relino.iuv.controllers;

import com.relino.iuv.entities.CatMunicipioPais;
import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.controllers.util.PaginationHelper;
import com.relino.iuv.entities.CatEstadosPais;
import com.relino.iuv.facades.CatMunicipioPaisFacade;

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

@Named("catMunicipioPaisController")
@SessionScoped
public class CatMunicipioPaisController implements Serializable {

    private CatMunicipioPais current;
    private DataModel items = null;
    @EJB
    private com.relino.iuv.facades.CatMunicipioPaisFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<CatMunicipioPais> muniListAjaxNac = null;
    private List<CatMunicipioPais> muniListAjaxDom = null;
    private List<CatMunicipioPais> muniListAjaxRef = null;

    public CatMunicipioPaisController() {
    }

//*********************** BUSCA MUNICIPIOS POR ENTIDAD PARA REFERENCIAS *************
//    public void buscaMunibyEntidadRefEdit(CatEstadosPais idEstadoPais) {
//        Integer idEstadoPaisEdit = idEstadoPais.getIdEstadoPais();
//        this.muniListAjaxRef = ejbFacade.findByEstadoPais(idEstadoPaisEdit);
//    }
//
//    public void buscaMunibyEntidadRef(AjaxBehaviorEvent evn) {
//        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
//        Integer idEstadoPais = objEntidad.getIdEstadoPais();
//        this.muniListAjaxRef = ejbFacade.findByEstadoPais(idEstadoPais);
//    }
//
//    public SelectItem[] getItemsMuniRefListSelectCats() {
//        return muniListAjaxRef != null ? JsfUtil.getSelectItemsCats(this.muniListAjaxRef, false) : null;
//    }
//
//    public List<CatMunicipioPais> getMuniListAjaxRef() {
//        return muniListAjaxRef;
//    }
//
//    public void setMuniListAjaxRef(List<CatMunicipioPais> muniListAjaxRef) {
//        this.muniListAjaxRef = muniListAjaxRef;
//    }
//*********************** BUSCA MUNICIPIOS POR ENTIDAD PARA UBICACION DE NACIMIENTOS *************

    public void buscaMunibyEntidadNac(AjaxBehaviorEvent evn) {
        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
        Integer idEstadoPais = objEntidad.getIdEstadoPais();
        this.muniListAjaxNac = ejbFacade.findByEstadoPais(idEstadoPais);
    }

    public SelectItem[] getItemsMuniNacListAjxSelectManyCats() {
        return muniListAjaxNac != null ? JsfUtil.getSelectItemsCats(this.muniListAjaxNac, false) : null;
    }

    public void buscaMunibyEntidadDom(AjaxBehaviorEvent evn) {
        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
        Integer idEstadoPais = objEntidad.getIdEstadoPais();
        this.muniListAjaxDom = ejbFacade.findByEstadoPais(idEstadoPais);
    }

    public SelectItem[] getItemsMuniListAjxSelectManyCatsDom() {
        return muniListAjaxDom != null ? JsfUtil.getSelectItemsCats(this.muniListAjaxDom, false) : null;
    }

    public void buscaMunibyEntidadRef(AjaxBehaviorEvent evn) {
        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
        Integer idEstadoPais = objEntidad.getIdEstadoPais();
        this.muniListAjaxRef = ejbFacade.findByEstadoPais(idEstadoPais);
    }

    public SelectItem[] getItemsMuniListAjxSelectManyCatsRef() {
        return muniListAjaxRef != null ? JsfUtil.getSelectItemsCats(this.muniListAjaxRef, false) : null;
    }

//
//    public List<CatMunicipioPais> getMuniListAjaxNac() {
//        return muniListAjaxNac;
//    }
//
//    public void setMuniListAjaxNac(List<CatMunicipioPais> muniListAjaxNac) {
//        this.muniListAjaxNac = muniListAjaxNac;
//    }
//    public void buscaMunibyEntidadEdit(CatEstadosPais idEstadoPais) {
//        Integer idEstadoPaisEdit = idEstadoPais.getIdEstadoPais();
//        this.muniListAjax = ejbFacade.findByEstadoPais(idEstadoPaisEdit);
//    }
//    public void buscaMunibyEntidad(AjaxBehaviorEvent evn) {
//        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
//        Integer idEstadoPais = objEntidad.getIdEstadoPais();
//        this.muniListAjax = ejbFacade.findByEstadoPais(idEstadoPais);
//   }
//
//    public void buscaMunibyEntidadDom(AjaxBehaviorEvent evn) {
//        CatEstadosPais objEntidad = (CatEstadosPais) ((UIOutput) evn.getSource()).getValue();
//        Integer idEstadoPais = objEntidad.getIdEstadoPais();
//        this.muniListAjax = ejbFacade.findByEstadoPais(idEstadoPais);
//    }
//
//    public SelectItem[] getItemsMuniListAjxSelectManyCats() {
//        return muniListAjax != null ? JsfUtil.getSelectItemsCats(this.muniListAjax, false) : null;
//    }
//
//    public SelectItem[] getItemsMuniListAjxSelectManyCatsDom() {
//        return muniListAjax != null ? JsfUtil.getSelectItemsCats(this.muniListAjax, false) : null;
//    } 

    public CatMunicipioPais getSelected() {
        if (current == null) {
            current = new CatMunicipioPais();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CatMunicipioPaisFacade getFacade() {
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
        current = (CatMunicipioPais) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CatMunicipioPais();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioPaisCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CatMunicipioPais) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioPaisUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CatMunicipioPais) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("CatMunicipioPaisDeleted"));
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

    public CatMunicipioPais getCatMunicipioPais(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = CatMunicipioPais.class)
    public static class CatMunicipioPaisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatMunicipioPaisController controller = (CatMunicipioPaisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catMunicipioPaisController");
            return controller.getCatMunicipioPais(getKey(value));
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
            if (object instanceof CatMunicipioPais) {
                CatMunicipioPais o = (CatMunicipioPais) object;
                return getStringKey(o.getIdMunicipioPais());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CatMunicipioPais.class.getName());
            }
        }

    }

}
