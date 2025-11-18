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
@Table(name = "CAT_ESCOLARIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEscolaridad.findAll", query = "SELECT c FROM CatEscolaridad c")
    , @NamedQuery(name = "CatEscolaridad.findByIdEscolaridad", query = "SELECT c FROM CatEscolaridad c WHERE c.idEscolaridad = :idEscolaridad")
    , @NamedQuery(name = "CatEscolaridad.findByEscolaridad", query = "SELECT c FROM CatEscolaridad c WHERE c.escolaridad = :escolaridad")
    , @NamedQuery(name = "CatEscolaridad.findByEstatus", query = "SELECT c FROM CatEscolaridad c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatEscolaridad.findByOrden", query = "SELECT c FROM CatEscolaridad c WHERE c.orden = :orden")})
public class CatEscolaridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESCOLARIDAD")
    private Integer idEscolaridad;
    @Size(max = 255)
    @Column(name = "ESCOLARIDAD")
    private String escolaridad;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idEscolaridad")
    private List<Aspirantes> aspirantesList;

    public CatEscolaridad() {
    }

    public CatEscolaridad(Integer idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public Integer getIdEscolaridad() {
        return idEscolaridad;
    }

    public void setIdEscolaridad(Integer idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
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

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscolaridad != null ? idEscolaridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEscolaridad)) {
            return false;
        }
        CatEscolaridad other = (CatEscolaridad) object;
        if ((this.idEscolaridad == null && other.idEscolaridad != null) || (this.idEscolaridad != null && !this.idEscolaridad.equals(other.idEscolaridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEscolaridad();
    }
    
}
