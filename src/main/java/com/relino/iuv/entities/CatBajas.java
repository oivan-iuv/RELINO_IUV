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
@Table(name = "CAT_BAJAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatBajas.findAll", query = "SELECT c FROM CatBajas c")
    , @NamedQuery(name = "CatBajas.findByIdBaja", query = "SELECT c FROM CatBajas c WHERE c.idBaja = :idBaja")
    , @NamedQuery(name = "CatBajas.findByBaja", query = "SELECT c FROM CatBajas c WHERE c.baja = :baja")
    , @NamedQuery(name = "CatBajas.findByEstatus", query = "SELECT c FROM CatBajas c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatBajas.findByOrden", query = "SELECT c FROM CatBajas c WHERE c.orden = :orden")})
public class CatBajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BAJA")
    private Integer idBaja;
    @Size(max = 255)
    @Column(name = "BAJA")
    private String baja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private int orden;
    @OneToMany(mappedBy = "idBaja")
    private List<CatSubBajas> catSubBajasList;

    public CatBajas() {
    }

    public CatBajas(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public CatBajas(Integer idBaja, Integer estatus, int orden) {
        this.idBaja = idBaja;
        this.estatus = estatus;
        this.orden = orden;
    }

    public Integer getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public String getBaja() {
        return baja;
    }

    public void setBaja(String baja) {
        this.baja = baja;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<CatSubBajas> getCatSubBajasList() {
        return catSubBajasList;
    }

    public void setCatSubBajasList(List<CatSubBajas> catSubBajasList) {
        this.catSubBajasList = catSubBajasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBaja != null ? idBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatBajas)) {
            return false;
        }
        CatBajas other = (CatBajas) object;
        if ((this.idBaja == null && other.idBaja != null) || (this.idBaja != null && !this.idBaja.equals(other.idBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatBajas[ idBaja=" + idBaja + " ]";
    }
    
}
