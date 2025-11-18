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
@Table(name = "CAT_MUNICIPIO_COLONIA_CP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatMunicipioColoniaCp.findAll", query = "SELECT c FROM CatMunicipioColoniaCp c")
    , @NamedQuery(name = "CatMunicipioColoniaCp.findByIdCatMunicipioColoniaCp", query = "SELECT c FROM CatMunicipioColoniaCp c WHERE c.idCatMunicipioColoniaCp = :idCatMunicipioColoniaCp")
    , @NamedQuery(name = "CatMunicipioColoniaCp.findByIdCatRegion", query = "SELECT c FROM CatMunicipioColoniaCp c WHERE c.idCatRegion = :idCatRegion")
    , @NamedQuery(name = "CatMunicipioColoniaCp.findByDescMuniipio", query = "SELECT c FROM CatMunicipioColoniaCp c WHERE c.descMuniipio = :descMuniipio")
    , @NamedQuery(name = "CatMunicipioColoniaCp.findByCodigoPostal", query = "SELECT c FROM CatMunicipioColoniaCp c WHERE c.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "CatMunicipioColoniaCp.findByColonia", query = "SELECT c FROM CatMunicipioColoniaCp c WHERE c.colonia = :colonia")})
public class CatMunicipioColoniaCp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT_MUNICIPIO_COLONIA_CP")
    private Integer idCatMunicipioColoniaCp;
    @Column(name = "ID_CAT_REGION")
    private Integer idCatRegion;
    @Size(max = 255)
    @Column(name = "DESC_MUNIIPIO")
    private String descMuniipio;
    @Column(name = "CODIGO_POSTAL")
    private Integer codigoPostal;
    @Size(max = 255)
    @Column(name = "COLONIA")
    private String colonia;
    @JoinColumn(name = "CLAVE_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")
    @ManyToOne
    private CatMunicipio claveMunicipio;

    public CatMunicipioColoniaCp() {
    }

    public CatMunicipioColoniaCp(Integer idCatMunicipioColoniaCp) {
        this.idCatMunicipioColoniaCp = idCatMunicipioColoniaCp;
    }

    public Integer getIdCatMunicipioColoniaCp() {
        return idCatMunicipioColoniaCp;
    }

    public void setIdCatMunicipioColoniaCp(Integer idCatMunicipioColoniaCp) {
        this.idCatMunicipioColoniaCp = idCatMunicipioColoniaCp;
    }

    public Integer getIdCatRegion() {
        return idCatRegion;
    }

    public void setIdCatRegion(Integer idCatRegion) {
        this.idCatRegion = idCatRegion;
    }

    public String getDescMuniipio() {
        return descMuniipio;
    }

    public void setDescMuniipio(String descMuniipio) {
        this.descMuniipio = descMuniipio;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public CatMunicipio getClaveMunicipio() {
        return claveMunicipio;
    }

    public void setClaveMunicipio(CatMunicipio claveMunicipio) {
        this.claveMunicipio = claveMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatMunicipioColoniaCp != null ? idCatMunicipioColoniaCp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMunicipioColoniaCp)) {
            return false;
        }
        CatMunicipioColoniaCp other = (CatMunicipioColoniaCp) object;
        if ((this.idCatMunicipioColoniaCp == null && other.idCatMunicipioColoniaCp != null) || (this.idCatMunicipioColoniaCp != null && !this.idCatMunicipioColoniaCp.equals(other.idCatMunicipioColoniaCp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatMunicipioColoniaCp[ idCatMunicipioColoniaCp=" + idCatMunicipioColoniaCp + " ]";
    }
    
}
