/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "ACTIVACION_NOMINAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivacionNominal.findAll", query = "SELECT a FROM ActivacionNominal a")
    , @NamedQuery(name = "ActivacionNominal.findByIdActivacion", query = "SELECT a FROM ActivacionNominal a WHERE a.idActivacion = :idActivacion")
    , @NamedQuery(name = "ActivacionNominal.findByNumeroOficio", query = "SELECT a FROM ActivacionNominal a WHERE a.numeroOficio = :numeroOficio")
    , @NamedQuery(name = "ActivacionNominal.findByFechaOficio", query = "SELECT a FROM ActivacionNominal a WHERE a.fechaOficio = :fechaOficio")
    , @NamedQuery(name = "ActivacionNominal.findByFechaActivacion", query = "SELECT a FROM ActivacionNominal a WHERE a.fechaActivacion = :fechaActivacion")})
public class ActivacionNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTIVACION")
    private Integer idActivacion;
    @Size(max = 255)
    @Column(name = "NUMERO_OFICIO")
    private String numeroOficio;
    @Column(name = "FECHA_OFICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOficio;
    @Column(name = "FECHA_ACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA")
    @ManyToOne
    private CatAreas idArea;
    

    public ActivacionNominal() {
    }

    public ActivacionNominal(Integer idActivacion) {
        this.idActivacion = idActivacion;
    }

    public Integer getIdActivacion() {
        return idActivacion;
    }

    public void setIdActivacion(Integer idActivacion) {
        this.idActivacion = idActivacion;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Date getFechaOficio() {
        return fechaOficio;
    }

    public void setFechaOficio(Date fechaOficio) {
        this.fechaOficio = fechaOficio;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public CatAreas getIdArea() {
        return idArea;
    }

    public void setIdArea(CatAreas idArea) {
        this.idArea = idArea;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivacion != null ? idActivacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivacionNominal)) {
            return false;
        }
        ActivacionNominal other = (ActivacionNominal) object;
        if ((this.idActivacion == null && other.idActivacion != null) || (this.idActivacion != null && !this.idActivacion.equals(other.idActivacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.ActivacionNominal[ idActivacion=" + idActivacion + " ]";
    }
    
}
