/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "NACIONAL_NO_NOMINAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NacionalNoNominal.findAll", query = "SELECT n FROM NacionalNoNominal n")
    , @NamedQuery(name = "NacionalNoNominal.findByIdNacNoNom", query = "SELECT n FROM NacionalNoNominal n WHERE n.idNacNoNom = :idNacNoNom")
    , @NamedQuery(name = "NacionalNoNominal.findByIdRnpsp", query = "SELECT n FROM NacionalNoNominal n WHERE n.idRnpsp = :idRnpsp")
    , @NamedQuery(name = "NacionalNoNominal.findByIdMunicipio", query = "SELECT n FROM NacionalNoNominal n WHERE n.idMunicipio = :idMunicipio")
    , @NamedQuery(name = "NacionalNoNominal.findByNacNombres", query = "SELECT n FROM NacionalNoNominal n WHERE n.nacNombres = :nacNombres")
    , @NamedQuery(name = "NacionalNoNominal.findByIdDepRelino", query = "SELECT n FROM NacionalNoNominal n WHERE n.idDepRelino = :idDepRelino")
    , @NamedQuery(name = "NacionalNoNominal.findByCorporacion", query = "SELECT n FROM NacionalNoNominal n WHERE n.corporacion = :corporacion")})
public class NacionalNoNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ID_NAC_NO_NOM")
    @Id
    private Integer idNacNoNom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RNPSP")
    private int idRnpsp;
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;
    @Size(max = 767)
    @Column(name = "NAC_NOMBRES")
    private String nacNombres;
    @Column(name = "ID_DEP_RELINO")
    private Integer idDepRelino;
    @Size(max = 255)
    @Column(name = "CORPORACION")
    private String corporacion;

    public NacionalNoNominal() {
    }

    public Integer getIdNacNoNom() {
        return idNacNoNom;
    }

    public void setIdNacNoNom(Integer idNacNoNom) {
        this.idNacNoNom = idNacNoNom;
    }

    public int getIdRnpsp() {
        return idRnpsp;
    }

    public void setIdRnpsp(int idRnpsp) {
        this.idRnpsp = idRnpsp;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNacNombres() {
        return nacNombres;
    }

    public void setNacNombres(String nacNombres) {
        this.nacNombres = nacNombres;
    }

    public Integer getIdDepRelino() {
        return idDepRelino;
    }

    public void setIdDepRelino(Integer idDepRelino) {
        this.idDepRelino = idDepRelino;
    }

    public String getCorporacion() {
        return corporacion;
    }

    public void setCorporacion(String corporacion) {
        this.corporacion = corporacion;
    }
    
}
