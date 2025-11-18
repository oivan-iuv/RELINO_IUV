/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

/**
 *
 * @author JAN
 */
@FacesValidator("validadorCampos")
public abstract class ValidadorCampos implements Validator{

    public boolean ValidaNumerico(String inTexto) {
        boolean Resultado = false;
        Pattern p = Pattern.compile("^([0-9]+)$");
        Matcher m = p.matcher(inTexto);
        if (m.find()) {
            Resultado = true;
        }
        m = null;
        p = null;
        return Resultado;
    }

    public boolean ValidaAlfanumerico(String inTexto) {
        boolean Resultado = false;
        Pattern p = Pattern.compile("^([a-zA-Z0-9" + new Character('\u00F1') + new Character('\u00D1') + "]+([a-zA-Z0-9" + new Character('\u00F1') + new Character('\u00D1') + "]|[\\s])*)$");
        Matcher m = p.matcher(inTexto);
        if (m.find()) {
            Resultado = true;
        }
        m = null;
        p = null;
        return Resultado;
    }

    public boolean ValidaCorreo(String inTexto) {
        boolean Resultado = false;
        //Pattern p = Pattern.compile("^(([a-zA-Z0-9_]+([-.]?[a-zA-Z0-9_]+)*)[@][([a-zA-Z0-9_]+([-.]?[a-zA-Z0-9_]+)*)])$");
        Pattern p = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher m = p.matcher(inTexto);
        if (m.find()) {
            Resultado = true;
        }
        m = null;
        p = null;
        return Resultado;
    }
    
    
}
