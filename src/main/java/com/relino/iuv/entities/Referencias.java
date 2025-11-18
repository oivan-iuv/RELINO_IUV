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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "REFERENCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referencias.findAll", query = "SELECT r FROM Referencias r")
    , @NamedQuery(name = "Referencias.findByIdReferencia", query = "SELECT r FROM Referencias r WHERE r.idReferencia = :idReferencia")
    , @NamedQuery(name = "Referencias.findByApaterno", query = "SELECT r FROM Referencias r WHERE r.apaterno = :apaterno")
    , @NamedQuery(name = "Referencias.findByAmaterno", query = "SELECT r FROM Referencias r WHERE r.amaterno = :amaterno")
    , @NamedQuery(name = "Referencias.findByNombre", query = "SELECT r FROM Referencias r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Referencias.findByOcupacion", query = "SELECT r FROM Referencias r WHERE r.ocupacion = :ocupacion")
    , @NamedQuery(name = "Referencias.findByParentesco", query = "SELECT r FROM Referencias r WHERE r.parentesco = :parentesco")
    , @NamedQuery(name = "Referencias.findByCalle", query = "SELECT r FROM Referencias r WHERE r.calle = :calle")
    , @NamedQuery(name = "Referencias.findByNoInterior", query = "SELECT r FROM Referencias r WHERE r.noInterior = :noInterior")
    , @NamedQuery(name = "Referencias.findByNoExterior", query = "SELECT r FROM Referencias r WHERE r.noExterior = :noExterior")
    , @NamedQuery(name = "Referencias.findByEntreCalle", query = "SELECT r FROM Referencias r WHERE r.entreCalle = :entreCalle")
    , @NamedQuery(name = "Referencias.findByYlaCalle", query = "SELECT r FROM Referencias r WHERE r.ylaCalle = :ylaCalle")
    , @NamedQuery(name = "Referencias.findByTelefono", query = "SELECT r FROM Referencias r WHERE r.telefono = :telefono")
    , @NamedQuery(name = "Referencias.findByCodigoPostal", query = "SELECT r FROM Referencias r WHERE r.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Referencias.findByEstatus", query = "SELECT r FROM Referencias r WHERE r.estatus = :estatus")
    , @NamedQuery(name = "Referencias.findByFechaRegistro", query = "SELECT r FROM Referencias r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Referencias.findByFechaModificacion", query = "SELECT r FROM Referencias r WHERE r.fechaModificacion = :fechaModificacion")})
public class Referencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_REFERENCIAS")
    @SequenceGenerator(name = "SEC_REFERENCIAS", sequenceName = "SEC_REFERENCIAS", allocationSize = 1)       
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REFERENCIA")
    private Integer idReferencia;
    @Size(max = 255)
    @Column(name = "APATERNO")
    private String apaterno;
    @Size(max = 255)
    @Column(name = "AMATERNO")
    private String amaterno;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "OCUPACION")
    private String ocupacion;
    @Size(max = 255)
    @Column(name = "PARENTESCO")
    private String parentesco;
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
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @ManyToMany(mappedBy = "referenciasList")
    private List<Aspirantes> aspirantesList;
    @JoinColumn(name = "ID_COLONIA", referencedColumnName = "ID_COLONIA_PAIS")
    @ManyToOne
    private CatColoniasPais idColonia;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID_ESTADO_PAIS")
    @ManyToOne
    private CatEstadosPais idEntidad;
    @JoinColumn(name = "ID_SEXO", referencedColumnName = "ID_GENERO")
    @ManyToOne
    private CatGenero idSexo;
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO_PAIS")
    @ManyToOne
    private CatMunicipioPais idMunicipio;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public Referencias() {
    }

    public Referencias(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
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

    public CatGenero getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(CatGenero idSexo) {
        this.idSexo = idSexo;
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
        hash += (idReferencia != null ? idReferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referencias)) {
            return false;
        }
        Referencias other = (Referencias) object;
        if ((this.idReferencia == null && other.idReferencia != null) || (this.idReferencia != null && !this.idReferencia.equals(other.idReferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Referencias[ idReferencia=" + idReferencia + " ]";
    }
    
}
