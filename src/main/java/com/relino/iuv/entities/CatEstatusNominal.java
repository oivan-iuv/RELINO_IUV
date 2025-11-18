/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "CAT_ESTATUS_NOMINAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEstatusNominal.findAll", query = "SELECT c FROM CatEstatusNominal c")
    , @NamedQuery(name = "CatEstatusNominal.findByIdEstatusNominal", query = "SELECT c FROM CatEstatusNominal c WHERE c.idEstatusNominal = :idEstatusNominal")
    , @NamedQuery(name = "CatEstatusNominal.findByEstatusNominal", query = "SELECT c FROM CatEstatusNominal c WHERE c.estatusNominal = :estatusNominal")
    , @NamedQuery(name = "CatEstatusNominal.findByEstatus", query = "SELECT c FROM CatEstatusNominal c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatEstatusNominal.findByOrden", query = "SELECT c FROM CatEstatusNominal c WHERE c.orden = :orden")})
public class CatEstatusNominal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS_NOMINAL")
    private Integer idEstatusNominal;
    @Size(max = 255)
    @Column(name = "ESTATUS_NOMINAL")
    private String estatusNominal;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;


    public CatEstatusNominal() {
    }

    public CatEstatusNominal(Integer idEstatusNominal) {
        this.idEstatusNominal = idEstatusNominal;
    }

    public Integer getIdEstatusNominal() {
        return idEstatusNominal;
    }

    public void setIdEstatusNominal(Integer idEstatusNominal) {
        this.idEstatusNominal = idEstatusNominal;
    }

    public String getEstatusNominal() {
        return estatusNominal;
    }

    public void setEstatusNominal(String estatusNominal) {
        this.estatusNominal = estatusNominal;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusNominal != null ? idEstatusNominal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstatusNominal)) {
            return false;
        }
        CatEstatusNominal other = (CatEstatusNominal) object;
        if ((this.idEstatusNominal == null && other.idEstatusNominal != null) || (this.idEstatusNominal != null && !this.idEstatusNominal.equals(other.idEstatusNominal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatEstatusNominal[ idEstatusNominal=" + idEstatusNominal + " ]";
    }
    
}
