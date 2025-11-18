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
@Table(name = "CAT_CORPORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatCorporacion.findAll", query = "SELECT c FROM CatCorporacion c")
    , @NamedQuery(name = "CatCorporacion.findByIdCorporacion", query = "SELECT c FROM CatCorporacion c WHERE c.idCorporacion = :idCorporacion")
    , @NamedQuery(name = "CatCorporacion.findByCorporacion", query = "SELECT c FROM CatCorporacion c WHERE c.corporacion = :corporacion")
    , @NamedQuery(name = "CatCorporacion.findByEstatus", query = "SELECT c FROM CatCorporacion c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatCorporacion.findByOrden", query = "SELECT c FROM CatCorporacion c WHERE c.orden = :orden")
    , @NamedQuery(name = "CatCorporacion.findByIdDependencia", query = "SELECT c FROM CatCorporacion c WHERE c.idDependencia = :idDependencia")
    , @NamedQuery(name = "CatCorporacion.findByShowtablero", query = "SELECT c FROM CatCorporacion c WHERE c.showtablero = :showtablero")})
public class CatCorporacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CORPORACION")
    private Integer idCorporacion;
    @Size(max = 255)
    @Column(name = "CORPORACION")
    private String corporacion;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @Column(name = "ID_DEPENDENCIA")
    private Integer idDependencia;
    @Column(name = "SHOWTABLERO")
    private Integer showtablero;
    @OneToMany(mappedBy = "idCorporacion")
    private List<Usuarios> usuariosList;
    @OneToMany(mappedBy = "idCorporacion")
    private List<NominalNoNacional> nominalNoNacionalList;
    @OneToMany(mappedBy = "idCorporacion")
    private List<Aspirantes> aspirantesList;


    public CatCorporacion() {
    }

    public CatCorporacion(Integer idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public Integer getIdCorporacion() {
        return idCorporacion;
    }

    public void setIdCorporacion(Integer idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public String getCorporacion() {
        return corporacion;
    }

    public void setCorporacion(String corporacion) {
        this.corporacion = corporacion;
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

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
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
    public List<NominalNoNacional> getNominalNoNacionalList() {
        return nominalNoNacionalList;
    }

    public void setNominalNoNacionalList(List<NominalNoNacional> nominalNoNacionalList) {
        this.nominalNoNacionalList = nominalNoNacionalList;
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
        hash += (idCorporacion != null ? idCorporacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCorporacion)) {
            return false;
        }
        CatCorporacion other = (CatCorporacion) object;
        if ((this.idCorporacion == null && other.idCorporacion != null) || (this.idCorporacion != null && !this.idCorporacion.equals(other.idCorporacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatCorporacion[ idCorporacion=" + idCorporacion + " ]";
    }
    
}
