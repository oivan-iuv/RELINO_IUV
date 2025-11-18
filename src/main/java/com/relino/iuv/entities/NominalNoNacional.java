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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NOMINAL_NO_NACIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NominalNoNacional.findAll", query = "SELECT n FROM NominalNoNacional n")
    , @NamedQuery(name = "NominalNoNacional.findByIdNomNoNac", query = "SELECT n FROM NominalNoNacional n WHERE n.idNomNoNac = :idNomNoNac")
    , @NamedQuery(name = "NominalNoNacional.findByIdEstadoFuerza", query = "SELECT n FROM NominalNoNacional n WHERE n.idEstadoFuerza = :idEstadoFuerza")
    , @NamedQuery(name = "NominalNoNacional.findByIdMunicipo", query = "SELECT n FROM NominalNoNacional n WHERE n.idMunicipo = :idMunicipo")
    , @NamedQuery(name = "NominalNoNacional.findByEfNombre", query = "SELECT n FROM NominalNoNacional n WHERE n.efNombre = :efNombre")
    , @NamedQuery(name = "NominalNoNacional.findByIdDependencia", query = "SELECT n FROM NominalNoNacional n WHERE n.idDependencia = :idDependencia")})
public class NominalNoNacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ID_NOM_NO_NAC")
    @Id
    private Integer idNomNoNac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_FUERZA")
    private long idEstadoFuerza;
    @Column(name = "ID_MUNICIPO")
    private Integer idMunicipo;
    @Size(max = 767)
    @Column(name = "EF_NOMBRE")
    private String efNombre;
    @Column(name = "ID_DEPENDENCIA")
    private Integer idDependencia;
    @JoinColumn(name = "ID_CORPORACION", referencedColumnName = "ID_CORPORACION")
    @ManyToOne
    private CatCorporacion idCorporacion;

    public NominalNoNacional() {
    }

    public Integer getIdNomNoNac() {
        return idNomNoNac;
    }

    public void setIdNomNoNac(Integer idNomNoNac) {
        this.idNomNoNac = idNomNoNac;
    }

    public long getIdEstadoFuerza() {
        return idEstadoFuerza;
    }

    public void setIdEstadoFuerza(long idEstadoFuerza) {
        this.idEstadoFuerza = idEstadoFuerza;
    }

    public Integer getIdMunicipo() {
        return idMunicipo;
    }

    public void setIdMunicipo(Integer idMunicipo) {
        this.idMunicipo = idMunicipo;
    }

    public String getEfNombre() {
        return efNombre;
    }

    public void setEfNombre(String efNombre) {
        this.efNombre = efNombre;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public CatCorporacion getIdCorporacion() {
        return idCorporacion;
    }

    public void setIdCorporacion(CatCorporacion idCorporacion) {
        this.idCorporacion = idCorporacion;
    }
    
}
