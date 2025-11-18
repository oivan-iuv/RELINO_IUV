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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ASPIRANTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aspirantes.findAll", query = "SELECT a FROM Aspirantes a")
    , @NamedQuery(name = "Aspirantes.findByIdAspirante", query = "SELECT a FROM Aspirantes a WHERE a.idAspirante = :idAspirante")
    , @NamedQuery(name = "Aspirantes.findByNombre", query = "SELECT a FROM Aspirantes a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Aspirantes.findByApaterno", query = "SELECT a FROM Aspirantes a WHERE a.apaterno = :apaterno")
    , @NamedQuery(name = "Aspirantes.findByAmaterno", query = "SELECT a FROM Aspirantes a WHERE a.amaterno = :amaterno")
    , @NamedQuery(name = "Aspirantes.findByFechaANominal", query = "SELECT a FROM Aspirantes a WHERE a.fechaANominal = :fechaANominal")
    , @NamedQuery(name = "Aspirantes.findByFechaCaptura", query = "SELECT a FROM Aspirantes a WHERE a.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "Aspirantes.findByFechaModificacion", query = "SELECT a FROM Aspirantes a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Aspirantes.findByIdResponsable", query = "SELECT a FROM Aspirantes a WHERE a.idResponsable = :idResponsable")
    , @NamedQuery(name = "Aspirantes.findByFecNacimiento", query = "SELECT a FROM Aspirantes a WHERE a.fecNacimiento = :fecNacimiento")
    , @NamedQuery(name = "Aspirantes.findByCurp", query = "SELECT a FROM Aspirantes a WHERE a.curp = :curp")
    , @NamedQuery(name = "Aspirantes.findByRfc", query = "SELECT a FROM Aspirantes a WHERE a.rfc = :rfc")
    , @NamedQuery(name = "Aspirantes.findByNoCartilla", query = "SELECT a FROM Aspirantes a WHERE a.noCartilla = :noCartilla")
    , @NamedQuery(name = "Aspirantes.findByCuip", query = "SELECT a FROM Aspirantes a WHERE a.cuip = :cuip")
    , @NamedQuery(name = "Aspirantes.findByVistoC4", query = "SELECT a FROM Aspirantes a WHERE a.vistoC4 = :vistoC4")
    , @NamedQuery(name = "Aspirantes.findByVistoMuni", query = "SELECT a FROM Aspirantes a WHERE a.vistoMuni = :vistoMuni")
    , @NamedQuery(name = "Aspirantes.findByFechaSolicitud", query = "SELECT a FROM Aspirantes a WHERE a.fechaSolicitud = :fechaSolicitud")
    , @NamedQuery(name = "Aspirantes.findByOficioSolicitud", query = "SELECT a FROM Aspirantes a WHERE a.oficioSolicitud = :oficioSolicitud")
    , @NamedQuery(name = "Aspirantes.findByMedioIngresoKardex", query = "SELECT a FROM Aspirantes a WHERE a.medioIngresoKardex = :medioIngresoKardex")
    , @NamedQuery(name = "Aspirantes.findByIdCandidatoKardex", query = "SELECT a FROM Aspirantes a WHERE a.idCandidatoKardex = :idCandidatoKardex")
    , @NamedQuery(name = "Aspirantes.findByIdPersonalKardex", query = "SELECT a FROM Aspirantes a WHERE a.idPersonalKardex = :idPersonalKardex")
    , @NamedQuery(name = "Aspirantes.findByFolioKardex", query = "SELECT a FROM Aspirantes a WHERE a.folioKardex = :folioKardex")
    , @NamedQuery(name = "Aspirantes.findByCelularKardex", query = "SELECT a FROM Aspirantes a WHERE a.celularKardex = :celularKardex")
    , @NamedQuery(name = "Aspirantes.findByEmailKardex", query = "SELECT a FROM Aspirantes a WHERE a.emailKardex = :emailKardex")
    , @NamedQuery(name = "Aspirantes.findByEstaturaKardex", query = "SELECT a FROM Aspirantes a WHERE a.estaturaKardex = :estaturaKardex")})
public class Aspirantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ASPIRANTES")
    @SequenceGenerator(name = "SEC_ASPIRANTES", sequenceName = "SEC_ASPIRANTES", allocationSize = 1)    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ASPIRANTE")
    private Integer idAspirante;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "APATERNO")
    private String apaterno;
    @Size(max = 255)
    @Column(name = "AMATERNO")
    private String amaterno;
    @Column(name = "FECHA_A_NOMINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaANominal;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "ID_RESPONSABLE")
    private Integer idResponsable;
    @Column(name = "FEC_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecNacimiento;
    @Size(max = 25)
    @Column(name = "CURP")
    private String curp;
    @Size(max = 25)
    @Column(name = "RFC")
    private String rfc;
    @Size(max = 255)
    @Column(name = "NO_CARTILLA")
    private String noCartilla;
    @Size(max = 25)
    @Column(name = "CUIP")
    private String cuip;
    @Column(name = "VISTO_C4")
    private Integer vistoC4;
    @Column(name = "VISTO_MUNI")
    private Integer vistoMuni;    
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Size(max = 255)
    @Column(name = "OFICIO_SOLICITUD")
    private String oficioSolicitud;
    @Column(name = "MEDIO_INGRESO_KARDEX")
    private Integer medioIngresoKardex;
    @Column(name = "ID_CANDIDATO_KARDEX")
    private Integer idCandidatoKardex;
    @Column(name = "ID_PERSONAL_KARDEX")
    private Integer idPersonalKardex;    
    @Size(max = 191)
    @Column(name = "FOLIO_KARDEX")
    private String folioKardex;
    @Size(max = 191)
    @Column(name = "CELULAR_KARDEX")
    private String celularKardex;
    @Size(max = 191)
    @Column(name = "EMAIL_KARDEX")
    private String emailKardex;
    @Size(max = 191)
    @Column(name = "ESTATURA_KARDEX")
    private String estaturaKardex;
    @JoinTable(name = "ASPIRANTES_REFERENCIAS", joinColumns = {
        @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_REFERENCIA", referencedColumnName = "ID_REFERENCIA")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Referencias> referenciasList;
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idAspirante")
//    private List<EstadoFuerza> estadoFuerzaList;
    @OneToMany(mappedBy = "idAspirante")
    private List<ProfAspirantes> profAspirantesList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idAspirante")
    private List<DictamenAspirante> dictamenAspiranteList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idAspirante")
    private List<Plantel> plantelList;
    @JoinColumn(name = "ID_ESCOLARIDAD", referencedColumnName = "ID_ESCOLARIDAD")
    @ManyToOne
    private CatEscolaridad idEscolaridad;
    @JoinColumn(name = "ID_DEPENDENCIA", referencedColumnName = "ID_DEPENDENCIA")
    @ManyToOne
    private CatDependencia idDependencia;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA")
    @ManyToOne
    private CatAreas idArea;
    @JoinColumn(name = "ID_CORPORACION", referencedColumnName = "ID_CORPORACION")
    @ManyToOne
    private CatCorporacion idCorporacion;
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID_ESTADO_CIVIL")
    @ManyToOne
    private CatEstadoCivil idEstadoCivil;
    @JoinColumn(name = "ID_ENTIDAD_NACIMIENTO", referencedColumnName = "ID_ESTADO_PAIS")
    @ManyToOne
    private CatEstadosPais idEntidadNacimiento;
    @JoinColumn(name = "ID_ESTATUS_ASPIRANTE", referencedColumnName = "ID_ESTATUS_ASPIRANTE")
    @ManyToOne
    private CatEstatusAspirante idEstatusAspirante;
//    @JoinColumn(name = "ID_ESTATUS_RNPSP", referencedColumnName = "ID_ESTATUS_RNPSP")
//    @ManyToOne
//    private CatEstatusRnpsp idEstatusRnpsp;
    @JoinColumn(name = "ID_SEXO", referencedColumnName = "ID_GENERO")
    @ManyToOne
    private CatGenero idSexo;
    @JoinColumn(name = "ID_MUNICIPIO_ASPIRANTE", referencedColumnName = "ID_MUNICIPIO")
    @ManyToOne
    private CatMunicipio idMunicipioAspirante;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;
    @JoinColumn(name = "ID_MUNICIPIO_NACIMIENTO", referencedColumnName = "ID_MUNICIPIO_PAIS")
    @ManyToOne
    private CatMunicipioPais idMunicipioNacimiento;
//    @OneToMany(mappedBy = "idAspirante")
//    private List<BitFlujoAspirantes> bitFlujoAspirantesList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idAspirante")
    private List<DomicilioAspirante> domicilioAspiranteList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idAspirante")
    private List<Fotografias> fotografiasList;

    public Aspirantes() {
    }

    public Aspirantes(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
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

    public Date getFechaANominal() {
        return fechaANominal;
    }

    public void setFechaANominal(Date fechaANominal) {
        this.fechaANominal = fechaANominal;
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

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNoCartilla() {
        return noCartilla;
    }

    public void setNoCartilla(String noCartilla) {
        this.noCartilla = noCartilla;
    }

    public String getCuip() {
        return cuip;
    }

    public void setCuip(String cuip) {
        this.cuip = cuip;
    }

    public Integer getVistoC4() {
        return vistoC4;
    }

    public void setVistoC4(Integer vistoC4) {
        this.vistoC4 = vistoC4;
    }

    public Integer getVistoMuni() {
        return vistoMuni;
    }

    public void setVistoMuni(Integer vistoMuni) {
        this.vistoMuni = vistoMuni;
    }
    
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getOficioSolicitud() {
        return oficioSolicitud;
    }

    public void setOficioSolicitud(String oficioSolicitud) {
        this.oficioSolicitud = oficioSolicitud;
    }

    public Integer getMedioIngresoKardex() {
        return medioIngresoKardex;
    }

    public void setMedioIngresoKardex(Integer medioIngresoKardex) {
        this.medioIngresoKardex = medioIngresoKardex;
    }

    public Integer getIdCandidatoKardex() {
        return idCandidatoKardex;
    }

    public void setIdCandidatoKardex(Integer idCandidatoKardex) {
        this.idCandidatoKardex = idCandidatoKardex;
    }

    public Integer getIdPersonalKardex() {
        return idPersonalKardex;
    }

    public void setIdPersonalKardex(Integer idPersonalKardex) {
        this.idPersonalKardex = idPersonalKardex;
    }
    
    public String getFolioKardex() {
        return folioKardex;
    }

    public void setFolioKardex(String folioKardex) {
        this.folioKardex = folioKardex;
    }

    public String getCelularKardex() {
        return celularKardex;
    }

    public void setCelularKardex(String celularKardex) {
        this.celularKardex = celularKardex;
    }

    public String getEmailKardex() {
        return emailKardex;
    }

    public void setEmailKardex(String emailKardex) {
        this.emailKardex = emailKardex;
    }

    public String getEstaturaKardex() {
        return estaturaKardex;
    }

    public void setEstaturaKardex(String estaturaKardex) {
        this.estaturaKardex = estaturaKardex;
    }

    @XmlTransient
    public List<Referencias> getReferenciasList() {
        return referenciasList;
    }

    public void setReferenciasList(List<Referencias> referenciasList) {
        this.referenciasList = referenciasList;
    }

//    @XmlTransient
//    public List<EstadoFuerza> getEstadoFuerzaList() {
//        return estadoFuerzaList;
//    }
//
//    public void setEstadoFuerzaList(List<EstadoFuerza> estadoFuerzaList) {
//        this.estadoFuerzaList = estadoFuerzaList;
//    }

    @XmlTransient
    public List<ProfAspirantes> getProfAspirantesList() {
        return profAspirantesList;
    }

    public void setProfAspirantesList(List<ProfAspirantes> profAspirantesList) {
        this.profAspirantesList = profAspirantesList;
    }

    @XmlTransient
    public List<DictamenAspirante> getDictamenAspiranteList() {
        return dictamenAspiranteList;
    }

    public void setDictamenAspiranteList(List<DictamenAspirante> dictamenAspiranteList) {
        this.dictamenAspiranteList = dictamenAspiranteList;
    }

    @XmlTransient
    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
    }

    public CatEscolaridad getIdEscolaridad() {
        return idEscolaridad;
    }

    public void setIdEscolaridad(CatEscolaridad idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public CatDependencia getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(CatDependencia idDependencia) {
        this.idDependencia = idDependencia;
    }

    public CatAreas getIdArea() {
        return idArea;
    }

    public void setIdArea(CatAreas idArea) {
        this.idArea = idArea;
    }

    public CatCorporacion getIdCorporacion() {
        return idCorporacion;
    }

    public void setIdCorporacion(CatCorporacion idCorporacion) {
        this.idCorporacion = idCorporacion;
    }

    public CatEstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(CatEstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public CatEstadosPais getIdEntidadNacimiento() {
        return idEntidadNacimiento;
    }

    public void setIdEntidadNacimiento(CatEstadosPais idEntidadNacimiento) {
        this.idEntidadNacimiento = idEntidadNacimiento;
    }

    public CatEstatusAspirante getIdEstatusAspirante() {
        return idEstatusAspirante;
    }

    public void setIdEstatusAspirante(CatEstatusAspirante idEstatusAspirante) {
        this.idEstatusAspirante = idEstatusAspirante;
    }

//    public CatEstatusRnpsp getIdEstatusRnpsp() {
//        return idEstatusRnpsp;
//    }
//
//    public void setIdEstatusRnpsp(CatEstatusRnpsp idEstatusRnpsp) {
//        this.idEstatusRnpsp = idEstatusRnpsp;
//    }

    public CatGenero getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(CatGenero idSexo) {
        this.idSexo = idSexo;
    }

    public CatMunicipio getIdMunicipioAspirante() {
        return idMunicipioAspirante;
    }

    public void setIdMunicipioAspirante(CatMunicipio idMunicipioAspirante) {
        this.idMunicipioAspirante = idMunicipioAspirante;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CatMunicipioPais getIdMunicipioNacimiento() {
        return idMunicipioNacimiento;
    }

    public void setIdMunicipioNacimiento(CatMunicipioPais idMunicipioNacimiento) {
        this.idMunicipioNacimiento = idMunicipioNacimiento;
    }

//    @XmlTransient
//    public List<BitFlujoAspirantes> getBitFlujoAspirantesList() {
//        return bitFlujoAspirantesList;
//    }
//
//    public void setBitFlujoAspirantesList(List<BitFlujoAspirantes> bitFlujoAspirantesList) {
//        this.bitFlujoAspirantesList = bitFlujoAspirantesList;
//    }

    @XmlTransient
    public List<DomicilioAspirante> getDomicilioAspiranteList() {
        return domicilioAspiranteList;
    }

    public void setDomicilioAspiranteList(List<DomicilioAspirante> domicilioAspiranteList) {
        this.domicilioAspiranteList = domicilioAspiranteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAspirante != null ? idAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aspirantes)) {
            return false;
        }
        Aspirantes other = (Aspirantes) object;
        if ((this.idAspirante == null && other.idAspirante != null) || (this.idAspirante != null && !this.idAspirante.equals(other.idAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Aspirantes[ idAspirante=" + idAspirante + " ]";
    }

    @XmlTransient
    public List<Fotografias> getFotografiasList() {
        return fotografiasList;
    }

    public void setFotografiasList(List<Fotografias> fotografiasList) {
        this.fotografiasList = fotografiasList;
    }
    
}
