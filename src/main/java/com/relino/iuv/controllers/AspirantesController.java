package com.relino.iuv.controllers;

import com.relino.iuv.entities.Aspirantes;
import static com.relino.iuv.controllers.util.JsfUtil.mensajeAUsuario;
import com.relino.iuv.controllers.util.JsfUtil;
import com.relino.iuv.controllers.util.PaginationHelper;
import com.relino.iuv.controllers.util.curp;
import com.relino.iuv.entities.CatColoniasPais;
import com.relino.iuv.entities.DomicilioAspirante;
import com.relino.iuv.entities.Fotografias;
import com.relino.iuv.entities.Plantel;
import com.relino.iuv.entities.Referencias;
import com.relino.iuv.facades.AspirantesFacade;
import com.relino.iuv.utils.SessionUsuario;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "aspirantesController", eager = true)
@SessionScoped
public class AspirantesController implements Serializable {

    @EJB
    private com.relino.iuv.facades.AspirantesFacade ejbFacade;
    @EJB
    private com.relino.iuv.facades.CatEstatusAspiranteFacade catEstatusAspiranteFacade;
    @EJB
    private com.relino.iuv.facades.EncabezadosFacade encabezadosFacade;
    @EJB
    private com.relino.iuv.facades.CatParametrosFacade catParametrosFacade;
    private Aspirantes current;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String fechaMinima;
    private String fechaMaxima;
    private String fechaMaximaPlantel;
    private String micurp;
    private String mensaje;
    private boolean btnAceptaCurp = true;
    private DomicilioAspirante currentDomicilio;
    private List<DomicilioAspirante> listDomicilioAspirante;
    private Fotografias currentFotografia;
    private List<Fotografias> listFotografias;
    private boolean disableUploadArchivo = false;
    private UploadedFile archivo;
    private StreamedContent imagen;
    private Plantel currentPlantel;
    private List<Plantel> listPlantel;
    private Referencias currentReferencia;
    private List<Referencias> listReferencias;
    private int rowIndexSelected;
    private final SessionUsuario session = new SessionUsuario();
    private List<Aspirantes> listVerificaExist;
    boolean datosCorrectos = true;
    private Integer idAspirantePdf;
    private String currentYear;

    public AspirantesController() {
    }

    @ManagedProperty(value = "#{catColoniasPaisController}")
    private CatColoniasPaisController catColoniasPaisController;

//    public CatColoniasPaisController getCatColoniasPaisController() {
//        return catColoniasPaisController;
//    }
    public void setCatColoniasPaisController(CatColoniasPaisController catColoniasPaisController) {
        this.catColoniasPaisController = catColoniasPaisController;
    }

    @PostConstruct
    private void inicializaDatos() {
        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        currentYear = getYearFormat.format(date);
        fechaMinima = catParametrosFacade.find(1).getNumeroParametro().toString();
        fechaMaxima = currentYear;
        SessionUsuario session = new SessionUsuario();

        if (current == null) {
            current = new Aspirantes();
            selectedItemIndex = -1;
        }
        if (listDomicilioAspirante == null) {
            listDomicilioAspirante = new ArrayList<>();
        }
        if (listPlantel == null) {
            listPlantel = new ArrayList<>();
        }
        if (listFotografias == null) {
            listFotografias = new ArrayList<>();
        }
        if (listReferencias == null) {
            listReferencias = new ArrayList<>();
        }
        if (currentFotografia == null) {
            currentFotografia = new Fotografias();
        }
    }

    public void validaEdad() {
        if (current.getFecNacimiento() != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(current.getFecNacimiento().toString(), fmt);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            System.out.printf("Tu edad es: %s años, %s meses y %s días",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
        }
    }

    public int validaDatos() {
        int valida = 0;
        //********************************** VALIDA CURP CORRECTA ******************************
//        if (datosCorrectos != verificaCurp()) {
//            valida++;
//        }
        //********************************** VALIDA ASPIRANTE REPETIDO *************************
        if (datosCorrectos != verificaExistAspirante(1)) { //********** POR RFC
            valida++;
        }
        if (datosCorrectos != verificaExistAspirante(2)) {  //********** POR CURP
            valida++;
        }

        return valida;
    }

    public boolean verificaCurp() {
        SimpleDateFormat getYearFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimiento = getYearFormat.format(current.getFecNacimiento());
        datosCorrectos = true;
        try {
            micurp = curp.generar(current.getApaterno(), current.getAmaterno(), current.getNombre(), current.getIdSexo().getGenero(), fechaNacimiento, current.getIdEntidadNacimiento().getAbreviatura());
        } catch (Exception ex) {
            Logger.getLogger(AspirantesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        if ((current.getCurp().substring(0, 17)).equals(micurp)) {
            datosCorrectos = true;
        } else {
            mensaje = "Esta es una CURP sugerida, calculada por el sistema, \n"
                    + "la CURP ingresada no cumple su formato de acuerdo a la información de datos personales ingresados";
            mensajeAUsuario("INFO", mensaje);
            PrimeFaces.current().executeScript("PF('wgbSugiereCurp').show();");
            datosCorrectos = true;// va false pero para pruebas se omitirá la validacion
        }

        return datosCorrectos;
    }

    public boolean verificaExistAspirante(int opc) {

        if (current.getCurp() != null || current.getRfc() != null) {
            listVerificaExist = ejbFacade.findIfAspExist(current, opc);
            if (!listVerificaExist.isEmpty()) {
                mensaje = opc == 1 ? "Ya existe un aspirante registrado con la RFC: " + current.getRfc() + "" : "Ya existe un aspirante registrado con la CURP: " + current.getCurp();
                current.setCurp("");
                current.setRfc("");
                current.setNombre("");
                current.setApaterno("");
                current.setAmaterno("");
                current.setFecNacimiento(null);
                datosCorrectos = false;
                session.getSession().removeAttribute("imagenBytes");
                PrimeFaces.current().executeScript("PF('wgbExisteAsp').show();");
            } else {
                datosCorrectos = true;
            }
        } else {
            datosCorrectos = false;
        }
        return datosCorrectos;
    }

    public void limpiaCP_Pais(int opc) {
        switch (opc) {
            case 1:
                currentDomicilio.setCodigoPostal(null);
                currentDomicilio.setIdColonia(null);
                catColoniasPaisController.setColoniaListAjaxDom(new ArrayList<CatColoniasPais>());
                break;
            case 2:
                currentReferencia.setCodigoPostal(null);
                currentReferencia.setIdColonia(null);
                catColoniasPaisController.setColoniaListAjaxRef(new ArrayList<CatColoniasPais>());
                break;
        }

    }

    public void asignaCP(int opc) {

        switch (opc) {
            case 1:
                currentDomicilio.setCodigoPostal(Integer.parseInt(currentDomicilio.getIdColonia().getCodigoPostalPais()));
                break;
            case 2:
                currentReferencia.setCodigoPostal(Integer.parseInt(currentReferencia.getIdColonia().getCodigoPostalPais()));
                break;
        }

    }

    public void guardaAspDep() throws JRException, IOException, SQLException {
        if (validaDatos() == 0) {
            currentDomicilio.setIdAspirante(current);
            currentPlantel.setIdAspirante(current);
            listDomicilioAspirante.add(currentDomicilio);
            listPlantel.add(currentPlantel);
            current.setDomicilioAspiranteList(listDomicilioAspirante);
            current.setPlantelList(listPlantel);
            current.setReferenciasList(listReferencias);
            current.setFotografiasList(listFotografias);
            /*
            Agregaremos mas tarde la procedenciadel usaurio
             */
            current.setIdDependencia(session.getDepSession());
            current.setIdCorporacion(session.getUaSession());
            current.setIdUsuario(session.getUsuarioSession());
            current.setIdMunicipioAspirante(session.getMuniSession());
            current.setIdEstatusAspirante(catEstatusAspiranteFacade.find(1));
            current.setFechaCaptura(new Date());
            create();
            createPdf();// aun en desarrollo
            mensajeAUsuario("INFO", "ASPIRANTE CREADO CORRECTAMENTE");
            PrimeFaces.current().ajax().addCallbackParam("generaCedula", true);
        } else {
            mensajeAUsuario("FATAL", "NO SE HA CREADO EL ASPIRANTE INTENTE NUEVAMENTE");
            PrimeFaces.current().ajax().addCallbackParam("generaCedula", false);
        }

    }

    public void createPdf() throws JRException, IOException, SQLException {
        String folderPDF = "\\Reportes\\altaAspirantes.jasper";
        Integer currentEncabezado;
        Map<String, Object> parametros = new HashMap<String, Object>();

        currentEncabezado = encabezadosFacade.findCurrentEncabezado(Integer.parseInt(currentYear)).getIdEncabezado();
        parametros.put("IdLogoEdomex", currentEncabezado);
        parametros.put("idAspirante", idAspirantePdf);

        String URL = catParametrosFacade.find(5).getDescripcionParametro();
        String user = catParametrosFacade.find(3).getDescripcionParametro();
        String passwd = catParametrosFacade.find(4).getDescripcionParametro();
        JsfUtil.creaPdfFile(folderPDF, parametros, URL, user, passwd);
    }

    public void subirImagenResize(FileUploadEvent event) {
        listFotografias = new ArrayList<>(); // inicializamos la lista para permitir un solo dato
        currentFotografia = new Fotografias();
        Date fechaCreacion = new Date();
        String nombreFile = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(event.getFile().getFileName());
        BufferedImage resizeImageHintJpg = null;
        byte[] imageInByte;

        try {
            BufferedImage bi = ImageIO.read(event.getFile().getInputStream());
            int type = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bi.getType();
            Integer IMG_HEIGHT = bi.getHeight();
            Integer IMG_WIDTH = bi.getWidth();
            resizeImageHintJpg = resizeImageWithHint(bi, type, IMG_HEIGHT, IMG_WIDTH);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizeImageHintJpg, ext, baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

            currentFotografia.setTitulo(event.getFile().getFileName());
            currentFotografia.setExtensionArchivo(FilenameUtils.getExtension(event.getFile().getFileName()));
            currentFotografia.setUuidNameImagen(nombreFile);
            currentFotografia.setTipoArchivo("image/" + ext);
            currentFotografia.setAnexoBytes(imageInByte);
            currentFotografia.setFechaCreacion(fechaCreacion);
            currentFotografia.setIdAspirante(current);
            listFotografias.add(currentFotografia);
            disableUploadArchivo = true;
            session.setAttribute("imagenBytes", imageInByte);

        } catch (IOException e) {
            System.out.println("Exception al subir imagenes: " + e.getMessage());
        }
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, Integer IMG_HEIGHT, Integer IMG_WIDTH) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }

    public void agregarReferencia() {
        listReferencias.add(currentReferencia);
        currentReferencia = new Referencias();
    }

    public void eliminaReferencia() {
        listReferencias.remove(rowIndexSelected);
    }

    public Aspirantes getSelected() {
        if (current == null) {
            current = new Aspirantes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AspirantesFacade getFacade() {
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
        current = (Aspirantes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Aspirantes();
        currentDomicilio = new DomicilioAspirante();
        currentPlantel = new Plantel();
        currentReferencia = new Referencias();
        currentFotografia = new Fotografias();
        listDomicilioAspirante = new ArrayList<>();
        listPlantel = new ArrayList<>();
        listFotografias = new ArrayList<>();
        listReferencias = new ArrayList<>();
        session.getSession().removeAttribute("imagenBytes");
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        idAspirantePdf = 0;
        try {
            getFacade().create(current);
            idAspirantePdf = current.getIdAspirante();
            //  mensajeAUsuario("INFO", "ASPIRANTE CREADO CORRECTAMENTE");
            return prepareCreate();
        } catch (Exception e) {
            mensajeAUsuario("FATAL", "NO SE HA CREADO EL ASPIRANTE INTENTE NUEVAMENTE");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Aspirantes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("AspirantesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/msg").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Aspirantes) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/msg").getString("AspirantesDeleted"));
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

    public Aspirantes getAspirantes(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Aspirantes.class)
    public static class AspirantesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AspirantesController controller = (AspirantesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aspirantesController");
            return controller.getAspirantes(getKey(value));
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
            if (object instanceof Aspirantes) {
                Aspirantes o = (Aspirantes) object;
                return getStringKey(o.getIdAspirante());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Aspirantes.class.getName());
            }
        }

    }

    public String getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(String fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getMicurp() {
        return micurp;
    }

    public void setMicurp(String micurp) {
        this.micurp = micurp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isBtnAceptaCurp() {
        return btnAceptaCurp;
    }

    public void setBtnAceptaCurp(boolean btnAceptaCurp) {
        this.btnAceptaCurp = btnAceptaCurp;
    }

    public DomicilioAspirante getSelectedDomicilio() {
        if (currentDomicilio == null) {
            currentDomicilio = new DomicilioAspirante();
        }
        return currentDomicilio;
    }

    public void setSelectedDomicilio(DomicilioAspirante currentDomicilio) {
        this.currentDomicilio = currentDomicilio;
    }

    public Fotografias getCurrentFotografia() {
        return currentFotografia;
    }

    public void setCurrentFotografia(Fotografias currentFotografia) {
        this.currentFotografia = currentFotografia;
    }

    public List<Fotografias> getListFotografias() {
        return listFotografias;
    }

    public void setListFotografias(List<Fotografias> listFotografias) {
        this.listFotografias = listFotografias;
    }

    public boolean isDisableUploadArchivo() {
        return disableUploadArchivo;
    }

    public void setDisableUploadArchivo(boolean disableUploadArchivo) {
        this.disableUploadArchivo = disableUploadArchivo;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public StreamedContent getImagen() {
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public Plantel getSelectedPlantel() {
        if (currentPlantel == null) {
            currentPlantel = new Plantel();
        }
        return currentPlantel;
    }

    public void setCurrentPlantel(Plantel currentPlantel) {
        this.currentPlantel = currentPlantel;
    }

    public Referencias getSelectedReferencia() {
        if (currentReferencia == null) {
            currentReferencia = new Referencias();
        }
        return currentReferencia;
    }

    public List<Referencias> getListReferencias() {
        return listReferencias;
    }

    public void setListReferencias(List<Referencias> listReferencias) {
        this.listReferencias = listReferencias;
    }

    public int getRowIndexSelected() {
        return rowIndexSelected;
    }

    public void setRowIndexSelected(int rowIndexSelected) {
        this.rowIndexSelected = rowIndexSelected;
    }

    public String getFechaMaximaPlantel() {
        return fechaMaximaPlantel;
    }

    public void setFechaMaximaPlantel(String fechaMaximaPlantel) {
        this.fechaMaximaPlantel = fechaMaximaPlantel;
    }

    public List<Aspirantes> getListVerificaExist() {
        return listVerificaExist;
    }

    public void setListVerificaExist(List<Aspirantes> listVerificaExist) {
        this.listVerificaExist = listVerificaExist;
    }

}
