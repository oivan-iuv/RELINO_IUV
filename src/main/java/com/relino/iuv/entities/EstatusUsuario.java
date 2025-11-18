/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ESTATUS_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusUsuario.findAll", query = "SELECT e FROM EstatusUsuario e")
    , @NamedQuery(name = "EstatusUsuario.findByIdEstatusUsuario", query = "SELECT e FROM EstatusUsuario e WHERE e.idEstatusUsuario = :idEstatusUsuario")
    , @NamedQuery(name = "EstatusUsuario.findByEstatusUsuario", query = "SELECT e FROM EstatusUsuario e WHERE e.estatusUsuario = :estatusUsuario")
    , @NamedQuery(name = "EstatusUsuario.findByActivo", query = "SELECT e FROM EstatusUsuario e WHERE e.activo = :activo")})
public class EstatusUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS_USUARIO")
    private Integer idEstatusUsuario;
    @Size(max = 25)
    @Column(name = "ESTATUS_USUARIO")
    private String estatusUsuario;
    @Column(name = "ACTIVO")
    private Integer activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatusUsuario", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public EstatusUsuario() {
    }

    public EstatusUsuario(Integer idEstatusUsuario) {
        this.idEstatusUsuario = idEstatusUsuario;
    }

    public Integer getIdEstatusUsuario() {
        return idEstatusUsuario;
    }

    public void setIdEstatusUsuario(Integer idEstatusUsuario) {
        this.idEstatusUsuario = idEstatusUsuario;
    }

    public String getEstatusUsuario() {
        return estatusUsuario;
    }

    public void setEstatusUsuario(String estatusUsuario) {
        this.estatusUsuario = estatusUsuario;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusUsuario != null ? idEstatusUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusUsuario)) {
            return false;
        }
        EstatusUsuario other = (EstatusUsuario) object;
        if ((this.idEstatusUsuario == null && other.idEstatusUsuario != null) || (this.idEstatusUsuario != null && !this.idEstatusUsuario.equals(other.idEstatusUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.EstatusUsuario[ idEstatusUsuario=" + idEstatusUsuario + " ]";
    }
    
}
