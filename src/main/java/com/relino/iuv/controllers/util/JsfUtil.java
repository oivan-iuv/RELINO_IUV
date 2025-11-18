package com.relino.iuv.controllers.util;

import com.relino.iuv.utils.SessionUsuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JsfUtil {

    static JasperPrint jasperPrint;
    static SessionUsuario session = new SessionUsuario();

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    /*
     * ******************** PROGRAMACIÓN PERSONALIZADA *************************
     */
    public static SelectItem[] getSelectItemsCats(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static String passToSHA(String value) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {

            return "";
        } catch (UnsupportedEncodingException ex) {

            return "";
        }

    } // fin de Método String passToSHA(String value)    

    public static void mensajeAUsuario(String tipo, String mess) {
        FacesMessage msg;

        if (tipo.equals("INFO")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, mess);
        } else if (tipo.equals("ERROR")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mess);
        } else if (tipo.equals("FATAL")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, mess);
        } else if (tipo.equals("WARN")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, null, mess);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, mess);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public static void creaPdfFile(String reportPathFolder, Map parametros, String URL, String user, String passwd) throws JRException, SQLException {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(URL, user, passwd);
        } catch (SQLException ex) {
            System.out.println("error al generar PDF: " + ex);
        }
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(reportPathFolder);
        jasperPrint = JasperFillManager.fillReport(reportPath, parametros, conn);
        byte[] reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        session.setAttribute("reportBytes", reportBytes);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("error SQL en la conexión al generar PDF: " + ex);
        } finally {
            conn.close();
        }
    }

}
