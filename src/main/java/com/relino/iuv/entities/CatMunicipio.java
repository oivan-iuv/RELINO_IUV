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
@Table(name = "CAT_MUNICIPIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatMunicipio.findAll", query = "SELECT c FROM CatMunicipio c")
    , @NamedQuery(name = "CatMunicipio.findByIdMunicipio", query = "SELECT c FROM CatMunicipio c WHERE c.idMunicipio = :idMunicipio")
    , @NamedQuery(name = "CatMunicipio.findByClaveMunicipio", query = "SELECT c FROM CatMunicipio c WHERE c.claveMunicipio = :claveMunicipio")
    , @NamedQuery(name = "CatMunicipio.findByMunicipio", query = "SELECT c FROM CatMunicipio c WHERE c.municipio = :municipio")
    , @NamedQuery(name = "CatMunicipio.findByEstatus", query = "SELECT c FROM CatMunicipio c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatMunicipio.findByShowtablero", query = "SELECT c FROM CatMunicipio c WHERE c.showtablero = :showtablero")})
public class CatMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;
    @Column(name = "CLAVE_MUNICIPIO")
    private Integer claveMunicipio;
    @Size(max = 255)
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "SHOWTABLERO")
    private Integer showtablero;
    @JoinColumn(name = "ID_ENTIDAD_FEDERATIVA", referencedColumnName = "ID_ENTIDAD_FEDERATIVA")
    @OneToMany(mappedBy = "idMunicipio")
    private List<Usuarios> usuariosList;
    @OneToMany(mappedBy = "idMunicipioAspirante")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "claveMunicipio")
    private List<CatMunicipioColoniaCp> catMunicipioColoniaCpList;

    public CatMunicipio() {
    }

    public CatMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getClaveMunicipio() {
        return claveMunicipio;
    }

    public void setClaveMunicipio(Integer claveMunicipio) {
        this.claveMunicipio = claveMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getShowtablero() {
        return showtablero;
    }

    public void setShowtablero(Integer showtablero) {
        this.showtablero = showtablero;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<CatMunicipioColoniaCp> getCatMunicipioColoniaCpList() {
        return catMunicipioColoniaCpList;
    }

    public void setCatMunicipioColoniaCpList(List<CatMunicipioColoniaCp> catMunicipioColoniaCpList) {
        this.catMunicipioColoniaCpList = catMunicipioColoniaCpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMunicipio)) {
            return false;
        }
        CatMunicipio other = (CatMunicipio) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatMunicipio[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
