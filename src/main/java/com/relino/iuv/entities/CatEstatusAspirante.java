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
@Table(name = "CAT_ESTATUS_ASPIRANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEstatusAspirante.findAll", query = "SELECT c FROM CatEstatusAspirante c")
    , @NamedQuery(name = "CatEstatusAspirante.findByIdEstatusAspirante", query = "SELECT c FROM CatEstatusAspirante c WHERE c.idEstatusAspirante = :idEstatusAspirante")
    , @NamedQuery(name = "CatEstatusAspirante.findByEstatusAspirante", query = "SELECT c FROM CatEstatusAspirante c WHERE c.estatusAspirante = :estatusAspirante")
    , @NamedQuery(name = "CatEstatusAspirante.findByEstatus", query = "SELECT c FROM CatEstatusAspirante c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatEstatusAspirante.findByOrden", query = "SELECT c FROM CatEstatusAspirante c WHERE c.orden = :orden")})
public class CatEstatusAspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS_ASPIRANTE")
    private Integer idEstatusAspirante;
    @Size(max = 255)
    @Column(name = "ESTATUS_ASPIRANTE")
    private String estatusAspirante;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idEstatusAspirante")
    private List<Aspirantes> aspirantesList;

    public CatEstatusAspirante() {
    }

    public CatEstatusAspirante(Integer idEstatusAspirante) {
        this.idEstatusAspirante = idEstatusAspirante;
    }

    public Integer getIdEstatusAspirante() {
        return idEstatusAspirante;
    }

    public void setIdEstatusAspirante(Integer idEstatusAspirante) {
        this.idEstatusAspirante = idEstatusAspirante;
    }

    public String getEstatusAspirante() {
        return estatusAspirante;
    }

    public void setEstatusAspirante(String estatusAspirante) {
        this.estatusAspirante = estatusAspirante;
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
        hash += (idEstatusAspirante != null ? idEstatusAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstatusAspirante)) {
            return false;
        }
        CatEstatusAspirante other = (CatEstatusAspirante) object;
        if ((this.idEstatusAspirante == null && other.idEstatusAspirante != null) || (this.idEstatusAspirante != null && !this.idEstatusAspirante.equals(other.idEstatusAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatEstatusAspirante[ idEstatusAspirante=" + idEstatusAspirante + " ]";
    }
    
}
