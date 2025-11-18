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
@Table(name = "CAT_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatRoles.findAll", query = "SELECT c FROM CatRoles c")
    , @NamedQuery(name = "CatRoles.findByIdRol", query = "SELECT c FROM CatRoles c WHERE c.idRol = :idRol")
    , @NamedQuery(name = "CatRoles.findByRol", query = "SELECT c FROM CatRoles c WHERE c.rol = :rol")
    , @NamedQuery(name = "CatRoles.findByEstatus", query = "SELECT c FROM CatRoles c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatRoles.findByDescripcion", query = "SELECT c FROM CatRoles c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CatRoles.findByOrden", query = "SELECT c FROM CatRoles c WHERE c.orden = :orden")})
public class CatRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private Integer idRol;
    @Size(max = 255)
    @Column(name = "ROL")
    private String rol;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idRol")
    private List<UsuarioRol> usuarioRolList;
    @JoinColumn(name = "ID_DEPENDENCIA", referencedColumnName = "ID_DEPENDENCIA")
    @ManyToOne
    private CatDependencia idDependencia;

    public CatRoles() {
    }

    public CatRoles(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    public CatDependencia getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(CatDependencia idDependencia) {
        this.idDependencia = idDependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatRoles)) {
            return false;
        }
        CatRoles other = (CatRoles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatRoles[ idRol=" + idRol + " ]";
    }
    
}
