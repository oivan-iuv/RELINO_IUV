/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CAT_DICTAMEN_ASPIRANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatDictamenAspirante.findAll", query = "SELECT c FROM CatDictamenAspirante c")
    , @NamedQuery(name = "CatDictamenAspirante.findByIdCatDictamenAsp", query = "SELECT c FROM CatDictamenAspirante c WHERE c.idCatDictamenAsp = :idCatDictamenAsp")
    , @NamedQuery(name = "CatDictamenAspirante.findByDictamenAspirante", query = "SELECT c FROM CatDictamenAspirante c WHERE c.dictamenAspirante = :dictamenAspirante")
    , @NamedQuery(name = "CatDictamenAspirante.findByEstatus", query = "SELECT c FROM CatDictamenAspirante c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatDictamenAspirante.findByOrden", query = "SELECT c FROM CatDictamenAspirante c WHERE c.orden = :orden")})
public class CatDictamenAspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT_DICTAMEN_ASP")
    private BigDecimal idCatDictamenAsp;
    @Size(max = 255)
    @Column(name = "DICTAMEN_ASPIRANTE")
    private String dictamenAspirante;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idCatDictamemAsp")
    private List<DictamenAspirante> dictamenAspiranteList;

    public CatDictamenAspirante() {
    }

    public CatDictamenAspirante(BigDecimal idCatDictamenAsp) {
        this.idCatDictamenAsp = idCatDictamenAsp;
    }

    public BigDecimal getIdCatDictamenAsp() {
        return idCatDictamenAsp;
    }

    public void setIdCatDictamenAsp(BigDecimal idCatDictamenAsp) {
        this.idCatDictamenAsp = idCatDictamenAsp;
    }

    public String getDictamenAspirante() {
        return dictamenAspirante;
    }

    public void setDictamenAspirante(String dictamenAspirante) {
        this.dictamenAspirante = dictamenAspirante;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatDictamenAsp != null ? idCatDictamenAsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDictamenAspirante)) {
            return false;
        }
        CatDictamenAspirante other = (CatDictamenAspirante) object;
        if ((this.idCatDictamenAsp == null && other.idCatDictamenAsp != null) || (this.idCatDictamenAsp != null && !this.idCatDictamenAsp.equals(other.idCatDictamenAsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatDictamenAspirante[ idCatDictamenAsp=" + idCatDictamenAsp + " ]";
    }
    
}
