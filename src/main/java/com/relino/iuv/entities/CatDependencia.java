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
@Table(name = "CAT_DEPENDENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatDependencia.findAll", query = "SELECT c FROM CatDependencia c")
    , @NamedQuery(name = "CatDependencia.findByIdDependencia", query = "SELECT c FROM CatDependencia c WHERE c.idDependencia = :idDependencia")
    , @NamedQuery(name = "CatDependencia.findByDependencia", query = "SELECT c FROM CatDependencia c WHERE c.dependencia = :dependencia")
    , @NamedQuery(name = "CatDependencia.findByEstatus", query = "SELECT c FROM CatDependencia c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatDependencia.findByOrden", query = "SELECT c FROM CatDependencia c WHERE c.orden = :orden")
    , @NamedQuery(name = "CatDependencia.findByDepRnpsp", query = "SELECT c FROM CatDependencia c WHERE c.depRnpsp = :depRnpsp")
    , @NamedQuery(name = "CatDependencia.findByResponsable", query = "SELECT c FROM CatDependencia c WHERE c.responsable = :responsable")
    , @NamedQuery(name = "CatDependencia.findByCargo", query = "SELECT c FROM CatDependencia c WHERE c.cargo = :cargo")
    , @NamedQuery(name = "CatDependencia.findByNomCorto", query = "SELECT c FROM CatDependencia c WHERE c.nomCorto = :nomCorto")
    , @NamedQuery(name = "CatDependencia.findByCorpmuni", query = "SELECT c FROM CatDependencia c WHERE c.corpmuni = :corpmuni")})
public class CatDependencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPENDENCIA")
    private Integer idDependencia;
    @Size(max = 255)
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @Size(max = 255)
    @Column(name = "DEP_RNPSP")
    private String depRnpsp;
    @Size(max = 255)
    @Column(name = "RESPONSABLE")
    private String responsable;
    @Size(max = 255)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 255)
    @Column(name = "NOM_CORTO")
    private String nomCorto;
    @Column(name = "CORPMUNI")
    private Integer corpmuni;
    @OneToMany(mappedBy = "idDependencia")
    private List<Usuarios> usuariosList;
    @OneToMany(mappedBy = "idDependencia")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idDependencia")
    private List<CatRoles> catRolesList;

    public CatDependencia() {
    }

    public CatDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
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

    public String getDepRnpsp() {
        return depRnpsp;
    }

    public void setDepRnpsp(String depRnpsp) {
        this.depRnpsp = depRnpsp;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNomCorto() {
        return nomCorto;
    }

    public void setNomCorto(String nomCorto) {
        this.nomCorto = nomCorto;
    }

    public Integer getCorpmuni() {
        return corpmuni;
    }

    public void setCorpmuni(Integer corpmuni) {
        this.corpmuni = corpmuni;
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
    public List<CatRoles> getCatRolesList() {
        return catRolesList;
    }

    public void setCatRolesList(List<CatRoles> catRolesList) {
        this.catRolesList = catRolesList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDependencia != null ? idDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDependencia)) {
            return false;
        }
        CatDependencia other = (CatDependencia) object;
        if ((this.idDependencia == null && other.idDependencia != null) || (this.idDependencia != null && !this.idDependencia.equals(other.idDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatDependencia[ idDependencia=" + idDependencia + " ]";
    }
    
}
