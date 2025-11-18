/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByApaterno", query = "SELECT u FROM Usuarios u WHERE u.apaterno = :apaterno"),
    @NamedQuery(name = "Usuarios.findByAmaterno", query = "SELECT u FROM Usuarios u WHERE u.amaterno = :amaterno"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByPass", query = "SELECT u FROM Usuarios u WHERE u.pass = :pass"),
    @NamedQuery(name = "Usuarios.findByEstatus", query = "SELECT u FROM Usuarios u WHERE u.estatus = :estatus"),
    @NamedQuery(name = "Usuarios.findByFecCreacion", query = "SELECT u FROM Usuarios u WHERE u.fecCreacion = :fecCreacion"),
    @NamedQuery(name = "Usuarios.findByFecModif", query = "SELECT u FROM Usuarios u WHERE u.fecModif = :fecModif"),
    @NamedQuery(name = "Usuarios.findByMacaddress", query = "SELECT u FROM Usuarios u WHERE u.macaddress = :macaddress"),
    @NamedQuery(name = "Usuarios.findByEstatusMac", query = "SELECT u FROM Usuarios u WHERE u.estatusMac = :estatusMac"),
    @NamedQuery(name = "Usuarios.findBySinReferencias", query = "SELECT u FROM Usuarios u WHERE u.sinReferencias = :sinReferencias"),
    @NamedQuery(name = "Usuarios.findByFechaBloqueo", query = "SELECT u FROM Usuarios u WHERE u.fechaBloqueo = :fechaBloqueo"),
    @NamedQuery(name = "Usuarios.findByEstatusBloqueo", query = "SELECT u FROM Usuarios u WHERE u.estatusBloqueo = :estatusBloqueo")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
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
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 255)
    @Column(name = "PASS")
    private String pass;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Column(name = "FEC_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecModif;
    @Size(max = 20)
    @Column(name = "MACADDRESS")
    private String macaddress;
    @Column(name = "ESTATUS_MAC")
    private Integer estatusMac;
    @Column(name = "SIN_REFERENCIAS")
    private Integer sinReferencias;
    @Column(name = "FECHA_BLOQUEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBloqueo;
    @Column(name = "ESTATUS_BLOQUEO")
    private Integer estatusBloqueo;
    @OneToMany(mappedBy = "idUsuario")
    @JoinColumn(name = "ID_CORPORACION", referencedColumnName = "ID_CORPORACION")
    @ManyToOne
    private CatCorporacion idCorporacion;
    @JoinColumn(name = "ID_DEPENDENCIA", referencedColumnName = "ID_DEPENDENCIA")
    @ManyToOne
    private CatDependencia idDependencia;
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")
    @ManyToOne
    private CatMunicipio idMunicipio;
    @JoinColumn(name = "ID_MUNI_ZONA", referencedColumnName = "ID_MUNI_ZONA")
    @OneToMany(mappedBy = "idUsuario")
    private List<Bitacora> bitacoraList;
    @OneToMany(mappedBy = "idUsuario")
    private List<UsuariosSesion> usuariosSesionList;
    @OneToMany(mappedBy = "idUsuario")
    private List<DatosUsuario> datosUsuarioList;
    @OneToMany(mappedBy = "idUsuario")
    private List<DictamenAspirante> dictamenAspiranteList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private UsuarioRol usuarioRol;
    @OneToMany(mappedBy = "idUsuario")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idUsuario")
    private List<ViewUsuariosRelino> viewUsuariosRelinoList;
    @OneToMany(mappedBy = "idUsuario")
    private List<DomicilioAspirante> domicilioAspiranteList;
    @OneToMany(mappedBy = "idUsuario")
    private List<Referencias> referenciasList;
    @JoinColumn(name = "ID_ESTATUS_USUARIO", referencedColumnName = "ID_ESTATUS_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusUsuario idEstatusUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<Contrasenia> contraseniaList;
    @Transient
    private Contrasenia contraseniaActiva;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Date getFecModif() {
        return fecModif;
    }

    public void setFecModif(Date fecModif) {
        this.fecModif = fecModif;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public Integer getEstatusMac() {
        return estatusMac;
    }

    public void setEstatusMac(Integer estatusMac) {
        this.estatusMac = estatusMac;
    }

    public Integer getSinReferencias() {
        return sinReferencias;
    }

    public void setSinReferencias(Integer sinReferencias) {
        this.sinReferencias = sinReferencias;
    }

    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public Integer getEstatusBloqueo() {
        return estatusBloqueo;
    }

    public void setEstatusBloqueo(Integer estatusBloqueo) {
        this.estatusBloqueo = estatusBloqueo;
    }
    public CatCorporacion getIdCorporacion() {
        return idCorporacion;
    }

    public void setIdCorporacion(CatCorporacion idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public CatDependencia getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(CatDependencia idDependencia) {
        this.idDependencia = idDependencia;
    }

    public CatMunicipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(CatMunicipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @XmlTransient
    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

    public void setBitacoraList(List<Bitacora> bitacoraList) {
        this.bitacoraList = bitacoraList;
    }

    @XmlTransient
    public List<UsuariosSesion> getUsuariosSesionList() {
        return usuariosSesionList;
    }

    public void setUsuariosSesionList(List<UsuariosSesion> usuariosSesionList) {
        this.usuariosSesionList = usuariosSesionList;
    }

    @XmlTransient
    public List<DatosUsuario> getDatosUsuarioList() {
        return datosUsuarioList;
    }

    public void setDatosUsuarioList(List<DatosUsuario> datosUsuarioList) {
        this.datosUsuarioList = datosUsuarioList;
    }

    @XmlTransient
    public List<DictamenAspirante> getDictamenAspiranteList() {
        return dictamenAspiranteList;
    }

    public void setDictamenAspiranteList(List<DictamenAspirante> dictamenAspiranteList) {
        this.dictamenAspiranteList = dictamenAspiranteList;
    }

      public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<ViewUsuariosRelino> getViewUsuariosRelinoList() {
        return viewUsuariosRelinoList;
    }

    public void setViewUsuariosRelinoList(List<ViewUsuariosRelino> viewUsuariosRelinoList) {
        this.viewUsuariosRelinoList = viewUsuariosRelinoList;
    }

    @XmlTransient
    public List<DomicilioAspirante> getDomicilioAspiranteList() {
        return domicilioAspiranteList;
    }

    public void setDomicilioAspiranteList(List<DomicilioAspirante> domicilioAspiranteList) {
        this.domicilioAspiranteList = domicilioAspiranteList;
    }

    @XmlTransient
    public List<Referencias> getReferenciasList() {
        return referenciasList;
    }

    public void setReferenciasList(List<Referencias> referenciasList) {
        this.referenciasList = referenciasList;
    }

    public EstatusUsuario getIdEstatusUsuario() {
        return idEstatusUsuario;
    }

    public void setIdEstatusUsuario(EstatusUsuario idEstatusUsuario) {
        this.idEstatusUsuario = idEstatusUsuario;
    }

    public List<Contrasenia> getContraseniaList() {
        return contraseniaList;
    }

    public void setContraseniaList(List<Contrasenia> contraseniaList) {
        this.contraseniaList = contraseniaList;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Usuarios[ idUsuario=" + idUsuario + " ]";
    }

    public Contrasenia getContraseniaActiva() {
        if (!this.contraseniaList.isEmpty()) {
            for (Contrasenia pass : this.contraseniaList) {
                if (pass.getActiva() == 1) {
                    contraseniaActiva = pass;
                    break;
                }
            }
        }
        return contraseniaActiva;
    }

}
