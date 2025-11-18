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
@Table(name = "CAT_CRITICIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatCriticidad.findAll", query = "SELECT c FROM CatCriticidad c")
    , @NamedQuery(name = "CatCriticidad.findByIdCriticidad", query = "SELECT c FROM CatCriticidad c WHERE c.idCriticidad = :idCriticidad")
    , @NamedQuery(name = "CatCriticidad.findByCriticidad", query = "SELECT c FROM CatCriticidad c WHERE c.criticidad = :criticidad")
    , @NamedQuery(name = "CatCriticidad.findByEstatus", query = "SELECT c FROM CatCriticidad c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatCriticidad.findByOrden", query = "SELECT c FROM CatCriticidad c WHERE c.orden = :orden")})
public class CatCriticidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CRITICIDAD")
    private Integer idCriticidad;
    @Size(max = 255)
    @Column(name = "CRITICIDAD")
    private String criticidad;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    

    public CatCriticidad() {
    }

    public CatCriticidad(Integer idCriticidad) {
        this.idCriticidad = idCriticidad;
    }

    public Integer getIdCriticidad() {
        return idCriticidad;
    }

    public void setIdCriticidad(Integer idCriticidad) {
        this.idCriticidad = idCriticidad;
    }

    public String getCriticidad() {
        return criticidad;
    }

    public void setCriticidad(String criticidad) {
        this.criticidad = criticidad;
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
        hash += (idCriticidad != null ? idCriticidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCriticidad)) {
            return false;
        }
        CatCriticidad other = (CatCriticidad) object;
        if ((this.idCriticidad == null && other.idCriticidad != null) || (this.idCriticidad != null && !this.idCriticidad.equals(other.idCriticidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatCriticidad[ idCriticidad=" + idCriticidad + " ]";
    }
    
}
