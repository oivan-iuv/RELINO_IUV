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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAT_SUB_BAJAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatSubBajas.findAll", query = "SELECT c FROM CatSubBajas c")
    , @NamedQuery(name = "CatSubBajas.findByIdSubBaja", query = "SELECT c FROM CatSubBajas c WHERE c.idSubBaja = :idSubBaja")
    , @NamedQuery(name = "CatSubBajas.findBySubBaja", query = "SELECT c FROM CatSubBajas c WHERE c.subBaja = :subBaja")
    , @NamedQuery(name = "CatSubBajas.findByEstatus", query = "SELECT c FROM CatSubBajas c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatSubBajas.findByOrden", query = "SELECT c FROM CatSubBajas c WHERE c.orden = :orden")})
public class CatSubBajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUB_BAJA")
    private Integer idSubBaja;
    @Size(max = 255)
    @Column(name = "SUB_BAJA")
    private String subBaja;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idSubBaja")
    private List<DictamenAspirante> dictamenAspiranteList;
    @OneToMany(mappedBy = "idSubBaja")
    @ManyToOne
    private CatBajas idBaja;

    public CatSubBajas() {
    }

    public CatSubBajas(Integer idSubBaja) {
        this.idSubBaja = idSubBaja;
    }

    public Integer getIdSubBaja() {
        return idSubBaja;
    }

    public void setIdSubBaja(Integer idSubBaja) {
        this.idSubBaja = idSubBaja;
    }

    public String getSubBaja() {
        return subBaja;
    }

    public void setSubBaja(String subBaja) {
        this.subBaja = subBaja;
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
    public List<DictamenAspirante> getDictamenAspiranteList() {
        return dictamenAspiranteList;
    }

    public void setDictamenAspiranteList(List<DictamenAspirante> dictamenAspiranteList) {
        this.dictamenAspiranteList = dictamenAspiranteList;
    }

    public CatBajas getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(CatBajas idBaja) {
        this.idBaja = idBaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubBaja != null ? idSubBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatSubBajas)) {
            return false;
        }
        CatSubBajas other = (CatSubBajas) object;
        if ((this.idSubBaja == null && other.idSubBaja != null) || (this.idSubBaja != null && !this.idSubBaja.equals(other.idSubBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatSubBajas[ idSubBaja=" + idSubBaja + " ]";
    }
    
}
