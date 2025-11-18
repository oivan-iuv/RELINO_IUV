/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "USUARIOS_SESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosSesion.findAll", query = "SELECT u FROM UsuariosSesion u")
    , @NamedQuery(name = "UsuariosSesion.findByIdUsuarioSesion", query = "SELECT u FROM UsuariosSesion u WHERE u.idUsuarioSesion = :idUsuarioSesion")
    , @NamedQuery(name = "UsuariosSesion.findBySesion", query = "SELECT u FROM UsuariosSesion u WHERE u.sesion = :sesion")
    , @NamedQuery(name = "UsuariosSesion.findByUltimaActividad", query = "SELECT u FROM UsuariosSesion u WHERE u.ultimaActividad = :ultimaActividad")})
public class UsuariosSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_SESION")
    private Long idUsuarioSesion;
    @Column(name = "SESION")
    private Integer sesion;
    @Column(name = "ULTIMA_ACTIVIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActividad;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public UsuariosSesion() {
    }

    public UsuariosSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public Integer getSesion() {
        return sesion;
    }

    public void setSesion(Integer sesion) {
        this.sesion = sesion;
    }

    public Date getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(Date ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioSesion != null ? idUsuarioSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosSesion)) {
            return false;
        }
        UsuariosSesion other = (UsuariosSesion) object;
        if ((this.idUsuarioSesion == null && other.idUsuarioSesion != null) || (this.idUsuarioSesion != null && !this.idUsuarioSesion.equals(other.idUsuarioSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.UsuariosSesion[ idUsuarioSesion=" + idUsuarioSesion + " ]";
    }
    
}
