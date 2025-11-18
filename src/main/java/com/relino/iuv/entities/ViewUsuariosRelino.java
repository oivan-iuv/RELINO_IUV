/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;

import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "VIEW_USUARIOS_RELINO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewUsuariosRelino.findAll", query = "SELECT v FROM ViewUsuariosRelino v")
    , @NamedQuery(name = "ViewUsuariosRelino.findByRownum", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.rownum = :rownum")
    , @NamedQuery(name = "ViewUsuariosRelino.findByNombreUsuario", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "ViewUsuariosRelino.findByUsuario", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.usuario = :usuario")
    , @NamedQuery(name = "ViewUsuariosRelino.findByRol", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.rol = :rol")
    , @NamedQuery(name = "ViewUsuariosRelino.findByDependencia", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.dependencia = :dependencia")
    , @NamedQuery(name = "ViewUsuariosRelino.findByMunicipio", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.municipio = :municipio")
    , @NamedQuery(name = "ViewUsuariosRelino.findByCargo", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.cargo = :cargo")
    , @NamedQuery(name = "ViewUsuariosRelino.findByTelOficina", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.telOficina = :telOficina")
    , @NamedQuery(name = "ViewUsuariosRelino.findByEmail", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.email = :email")
    , @NamedQuery(name = "ViewUsuariosRelino.findByEstatus", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.estatus = :estatus")
    , @NamedQuery(name = "ViewUsuariosRelino.findByFechaCaptura", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "ViewUsuariosRelino.findByFecModif", query = "SELECT v FROM ViewUsuariosRelino v WHERE v.fecModif = :fecModif")})
public class ViewUsuariosRelino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ROWNUM")
    @Id
    private Integer rownum;
    @Size(max = 767)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Size(max = 255)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 255)
    @Column(name = "ROL")
    private String rol;
    @Size(max = 255)
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Size(max = 255)
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Size(max = 255)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 255)
    @Column(name = "TEL_OFICINA")
    private String telOficina;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FEC_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecModif;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public ViewUsuariosRelino() {
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFecModif() {
        return fecModif;
    }

    public void setFecModif(Date fecModif) {
        this.fecModif = fecModif;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
