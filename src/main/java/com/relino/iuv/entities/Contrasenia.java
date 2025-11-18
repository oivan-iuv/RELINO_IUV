/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JAN
 */
@Entity
@Cacheable (false)
@Table(name = "CONTRASENIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrasenia.findAll", query = "SELECT c FROM Contrasenia c"),
    @NamedQuery(name = "Contrasenia.findByIdContrasenia", query = "SELECT c FROM Contrasenia c WHERE c.idContrasenia = :idContrasenia"),
    @NamedQuery(name = "Contrasenia.findByFechaCambio", query = "SELECT c FROM Contrasenia c WHERE c.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "Contrasenia.findByPasswordAndId", query = "SELECT c FROM Contrasenia c WHERE c.password = :password and c.idUsuario.idUsuario = :idUsuario"),
    @NamedQuery(name = "Contrasenia.findByPasswordActiva", query = "SELECT c FROM Contrasenia c WHERE c.idUsuario.idUsuario = :idUsuario and c.activa = 1"),
    @NamedQuery(name = "Contrasenia.findByPassword", query = "SELECT c FROM Contrasenia c WHERE c.password = :password")})
public class Contrasenia implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_CONTRASENIA")
    @SequenceGenerator(name = "SEC_CONTRASENIA", sequenceName = "SEC_CONTRASENIA", allocationSize = 1)
    @Column(name = "ID_CONTRASENIA")
    private BigDecimal idContrasenia;
    @Column(name = "FECHA_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Size(max = 150)
    @Column(name = "CONTRASENIA")
    private String password;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVA")
    private short activa;

    public Contrasenia() {
    }

    public Contrasenia(BigDecimal idContrasenia) {
        this.idContrasenia = idContrasenia;
    }

    public BigDecimal getIdContrasenia() {
        return idContrasenia;
    }

    public void setIdContrasenia(BigDecimal idContrasenia) {
        this.idContrasenia = idContrasenia;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuarios getIdUsuarios() {
        return idUsuario;
    }

    public void setIdUsuarios(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrasenia != null ? idContrasenia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrasenia)) {
            return false;
        }
        Contrasenia other = (Contrasenia) object;
        if ((this.idContrasenia == null && other.idContrasenia != null) || (this.idContrasenia != null && !this.idContrasenia.equals(other.idContrasenia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contrasenia[ idContrasenia=" + idContrasenia + " ]";
    }

    public short getActiva() {
        return activa;
    }

    public void setActiva(short activa) {
        this.activa = activa;
    }
}
