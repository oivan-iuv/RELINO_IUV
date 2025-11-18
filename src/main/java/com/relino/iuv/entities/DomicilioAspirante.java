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
 * @author IvanPC
 */
@Entity
@Table(name = "DOMICILIO_ASPIRANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DomicilioAspirante.findAll", query = "SELECT d FROM DomicilioAspirante d")
    , @NamedQuery(name = "DomicilioAspirante.findByIdDomicilio", query = "SELECT d FROM DomicilioAspirante d WHERE d.idDomicilio = :idDomicilio")
    , @NamedQuery(name = "DomicilioAspirante.findByCalle", query = "SELECT d FROM DomicilioAspirante d WHERE d.calle = :calle")
    , @NamedQuery(name = "DomicilioAspirante.findByNoInterior", query = "SELECT d FROM DomicilioAspirante d WHERE d.noInterior = :noInterior")
    , @NamedQuery(name = "DomicilioAspirante.findByNoExterior", query = "SELECT d FROM DomicilioAspirante d WHERE d.noExterior = :noExterior")
    , @NamedQuery(name = "DomicilioAspirante.findByOtraColonia", query = "SELECT d FROM DomicilioAspirante d WHERE d.otraColonia = :otraColonia")
    , @NamedQuery(name = "DomicilioAspirante.findByEntreCalle", query = "SELECT d FROM DomicilioAspirante d WHERE d.entreCalle = :entreCalle")
    , @NamedQuery(name = "DomicilioAspirante.findByYlaCalle", query = "SELECT d FROM DomicilioAspirante d WHERE d.ylaCalle = :ylaCalle")
    , @NamedQuery(name = "DomicilioAspirante.findByTelefono", query = "SELECT d FROM DomicilioAspirante d WHERE d.telefono = :telefono")
    , @NamedQuery(name = "DomicilioAspirante.findByCodigoPostal", query = "SELECT d FROM DomicilioAspirante d WHERE d.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "DomicilioAspirante.findByEstatus", query = "SELECT d FROM DomicilioAspirante d WHERE d.estatus = :estatus")
    , @NamedQuery(name = "DomicilioAspirante.findByFechaCaptura", query = "SELECT d FROM DomicilioAspirante d WHERE d.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "DomicilioAspirante.findByFechaModificacion", query = "SELECT d FROM DomicilioAspirante d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "DomicilioAspirante.findByLatitud", query = "SELECT d FROM DomicilioAspirante d WHERE d.latitud = :latitud")
    , @NamedQuery(name = "DomicilioAspirante.findByLongitud", query = "SELECT d FROM DomicilioAspirante d WHERE d.longitud = :longitud")})
public class DomicilioAspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_DOMI_ASP")
    @SequenceGenerator(name = "SEC_DOMI_ASP", sequenceName = "SEC_DOMI_ASP", allocationSize = 1)      
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOMICILIO")
    private Integer idDomicilio;
    @Size(max = 255)
    @Column(name = "CALLE")
    private String calle;
    @Size(max = 255)
    @Column(name = "NO_INTERIOR")
    private String noInterior;
    @Size(max = 255)
    @Column(name = "NO_EXTERIOR")
    private String noExterior;
    @Size(max = 255)
    @Column(name = "OTRA_COLONIA")
    private String otraColonia;
    @Size(max = 255)
    @Column(name = "ENTRE_CALLE")
    private String entreCalle;
    @Size(max = 255)
    @Column(name = "YLA_CALLE")
    private String ylaCalle;
    @Size(max = 255)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CODIGO_POSTAL")
    private Integer codigoPostal;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUD")
    private Double latitud;
    @Column(name = "LONGITUD")
    private Double longitud;
    @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")
    @ManyToOne
    private Aspirantes idAspirante;
    @JoinColumn(name = "ID_COLONIA", referencedColumnName = "ID_COLONIA_PAIS")
    @ManyToOne
    private CatColoniasPais idColonia;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID_ESTADO_PAIS")
    @ManyToOne
    private CatEstadosPais idEntidad;
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO_PAIS")
    @ManyToOne
    private CatMunicipioPais idMunicipio;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public DomicilioAspirante() {
    }

    public DomicilioAspirante(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getOtraColonia() {
        return otraColonia;
    }

    public void setOtraColonia(String otraColonia) {
        this.otraColonia = otraColonia;
    }

    public String getEntreCalle() {
        return entreCalle;
    }

    public void setEntreCalle(String entreCalle) {
        this.entreCalle = entreCalle;
    }

    public String getYlaCalle() {
        return ylaCalle;
    }

    public void setYlaCalle(String ylaCalle) {
        this.ylaCalle = ylaCalle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Aspirantes getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirantes idAspirante) {
        this.idAspirante = idAspirante;
    }

    public CatColoniasPais getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(CatColoniasPais idColonia) {
        this.idColonia = idColonia;
    }

    public CatEstadosPais getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(CatEstadosPais idEntidad) {
        this.idEntidad = idEntidad;
    }

    public CatMunicipioPais getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(CatMunicipioPais idMunicipio) {
        this.idMunicipio = idMunicipio;
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
        hash += (idDomicilio != null ? idDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DomicilioAspirante)) {
            return false;
        }
        DomicilioAspirante other = (DomicilioAspirante) object;
        if ((this.idDomicilio == null && other.idDomicilio != null) || (this.idDomicilio != null && !this.idDomicilio.equals(other.idDomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.DomicilioAspirante[ idDomicilio=" + idDomicilio + " ]";
    }
    
}
