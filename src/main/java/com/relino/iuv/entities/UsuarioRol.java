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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
    , @NamedQuery(name = "UsuarioRol.findByIdUsuario", query = "SELECT u FROM UsuarioRol u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "UsuarioRol.findByEscritura", query = "SELECT u FROM UsuarioRol u WHERE u.escritura = :escritura")
    , @NamedQuery(name = "UsuarioRol.findByIdUsRol", query = "SELECT u FROM UsuarioRol u WHERE u.idUsRol = :idUsRol")
    , @NamedQuery(name = "UsuarioRol.findByDiaBloqueo", query = "SELECT u FROM UsuarioRol u WHERE u.diaBloqueo = :diaBloqueo")})
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "ESCRITURA")
    private Integer escritura;
    @Column(name = "ID_US_ROL")
    private Integer idUsRol;
    @Column(name = "DIA_BLOQUEO")
    private Integer diaBloqueo;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private CatRoles idRol;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuarios usuarios;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getEscritura() {
        return escritura;
    }

    public void setEscritura(Integer escritura) {
        this.escritura = escritura;
    }

    public Integer getIdUsRol() {
        return idUsRol;
    }

    public void setIdUsRol(Integer idUsRol) {
        this.idUsRol = idUsRol;
    }

    public Integer getDiaBloqueo() {
        return diaBloqueo;
    }

    public void setDiaBloqueo(Integer diaBloqueo) {
        this.diaBloqueo = diaBloqueo;
    }

    public CatRoles getIdRol() {
        return idRol;
    }

    public void setIdRol(CatRoles idRol) {
        this.idRol = idRol;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.UsuarioRol[ idUsuario=" + idUsuario + " ]";
    }
    
}
