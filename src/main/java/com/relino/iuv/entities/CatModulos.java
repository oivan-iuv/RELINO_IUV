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
@Table(name = "CAT_MODULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatModulos.findAll", query = "SELECT c FROM CatModulos c")
    , @NamedQuery(name = "CatModulos.findByIdModulo", query = "SELECT c FROM CatModulos c WHERE c.idModulo = :idModulo")
    , @NamedQuery(name = "CatModulos.findByModulo", query = "SELECT c FROM CatModulos c WHERE c.modulo = :modulo")})
public class CatModulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MODULO")
    private Integer idModulo;
    @Size(max = 255)
    @Column(name = "MODULO")
    private String modulo;
    @OneToMany(mappedBy = "idModulo")
    private List<Bitacora> bitacoraList;

    public CatModulos() {
    }

    public CatModulos(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    @XmlTransient
    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

    public void setBitacoraList(List<Bitacora> bitacoraList) {
        this.bitacoraList = bitacoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatModulos)) {
            return false;
        }
        CatModulos other = (CatModulos) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatModulos[ idModulo=" + idModulo + " ]";
    }
    
}
