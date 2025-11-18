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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "DATOS_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosUsuario.findAll", query = "SELECT d FROM DatosUsuario d")
    , @NamedQuery(name = "DatosUsuario.findByIdDatosUsuario", query = "SELECT d FROM DatosUsuario d WHERE d.idDatosUsuario = :idDatosUsuario")
    , @NamedQuery(name = "DatosUsuario.findByNombre", query = "SELECT d FROM DatosUsuario d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DatosUsuario.findByApaterno", query = "SELECT d FROM DatosUsuario d WHERE d.apaterno = :apaterno")
    , @NamedQuery(name = "DatosUsuario.findByAmaterno", query = "SELECT d FROM DatosUsuario d WHERE d.amaterno = :amaterno")
    , @NamedQuery(name = "DatosUsuario.findByCargo", query = "SELECT d FROM DatosUsuario d WHERE d.cargo = :cargo")
    , @NamedQuery(name = "DatosUsuario.findByTelCell", query = "SELECT d FROM DatosUsuario d WHERE d.telCell = :telCell")
    , @NamedQuery(name = "DatosUsuario.findByTelOficina", query = "SELECT d FROM DatosUsuario d WHERE d.telOficina = :telOficina")
    , @NamedQuery(name = "DatosUsuario.findByEmail", query = "SELECT d FROM DatosUsuario d WHERE d.email = :email")
    , @NamedQuery(name = "DatosUsuario.findByFechaCaptura", query = "SELECT d FROM DatosUsuario d WHERE d.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "DatosUsuario.findByFechaModif", query = "SELECT d FROM DatosUsuario d WHERE d.fechaModif = :fechaModif")
    , @NamedQuery(name = "DatosUsuario.findByEstatus", query = "SELECT d FROM DatosUsuario d WHERE d.estatus = :estatus")
    , @NamedQuery(name = "DatosUsuario.findByFechaInabilitacion", query = "SELECT d FROM DatosUsuario d WHERE d.fechaInabilitacion = :fechaInabilitacion")})
public class DatosUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DATOS_USUARIO")
    private Integer idDatosUsuario;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "APATERNO")
    private String apaterno;
    @Size(max = 255)
    @Column(name = "AMATERNO")
    private String amaterno;
    @Size(max = 255)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 25)
    @Column(name = "TEL_CELL")
    private String telCell;
    @Size(max = 255)
    @Column(name = "TEL_OFICINA")
    private String telOficina;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FECHA_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModif;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "FECHA_INABILITACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInabilitacion;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public DatosUsuario() {
    }

    public DatosUsuario(Integer idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public Integer getIdDatosUsuario() {
        return idDatosUsuario;
    }

    public void setIdDatosUsuario(Integer idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelCell() {
        return telCell;
    }

    public void setTelCell(String telCell) {
        this.telCell = telCell;
    }

    public String getTelOficina() {
        return telOficina;
    }

    public void setTelOficina(String telOficina) {
        this.telOficina = telOficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Date fechaModif) {
        this.fechaModif = fechaModif;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaInabilitacion() {
        return fechaInabilitacion;
    }

    public void setFechaInabilitacion(Date fechaInabilitacion) {
        this.fechaInabilitacion = fechaInabilitacion;
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
        hash += (idDatosUsuario != null ? idDatosUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosUsuario)) {
            return false;
        }
        DatosUsuario other = (DatosUsuario) object;
        if ((this.idDatosUsuario == null && other.idDatosUsuario != null) || (this.idDatosUsuario != null && !this.idDatosUsuario.equals(other.idDatosUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.DatosUsuario[ idDatosUsuario=" + idDatosUsuario + " ]";
    }
    
}
