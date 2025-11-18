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
@Table(name = "PLANTEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantel.findAll", query = "SELECT p FROM Plantel p")
    , @NamedQuery(name = "Plantel.findByIdPlantel", query = "SELECT p FROM Plantel p WHERE p.idPlantel = :idPlantel")
    , @NamedQuery(name = "Plantel.findByDependenciaResponsable", query = "SELECT p FROM Plantel p WHERE p.dependenciaResponsable = :dependenciaResponsable")
    , @NamedQuery(name = "Plantel.findByInstitucionCapacitadora", query = "SELECT p FROM Plantel p WHERE p.institucionCapacitadora = :institucionCapacitadora")
    , @NamedQuery(name = "Plantel.findByCurso", query = "SELECT p FROM Plantel p WHERE p.curso = :curso")
    , @NamedQuery(name = "Plantel.findByFechaInicio", query = "SELECT p FROM Plantel p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Plantel.findByFechaFin", query = "SELECT p FROM Plantel p WHERE p.fechaFin = :fechaFin")
    , @NamedQuery(name = "Plantel.findByFechaCaptura", query = "SELECT p FROM Plantel p WHERE p.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "Plantel.findByFechaModificacion", query = "SELECT p FROM Plantel p WHERE p.fechaModificacion = :fechaModificacion")})
public class Plantel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_PLANTEL")
    @SequenceGenerator(name = "SEC_PLANTEL", sequenceName = "SEC_PLANTEL", allocationSize = 1)     
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PLANTEL")
    private Integer idPlantel;
    @Size(max = 255)
    @Column(name = "DEPENDENCIA_RESPONSABLE")
    private String dependenciaResponsable;
    @Size(max = 255)
    @Column(name = "INSTITUCION_CAPACITADORA")
    private String institucionCapacitadora;
    @Size(max = 255)
    @Column(name = "CURSO")
    private String curso;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")
    @ManyToOne
    private Aspirantes idAspirante;
    @JoinColumn(name = "ID_EFICIENCIA", referencedColumnName = "ID_EFICIENCIA")
    @ManyToOne
    private CatEficiencia idEficiencia;
    @JoinColumn(name = "ID_NIVEL_CURSO", referencedColumnName = "ID_NIVEL_CURSO")
    @ManyToOne
    private CatNivelCurso idNivelCurso;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public Plantel() {
    }

    public Plantel(Integer idPlantel) {
        this.idPlantel = idPlantel;
    }

    public Integer getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Integer idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getDependenciaResponsable() {
        return dependenciaResponsable;
    }

    public void setDependenciaResponsable(String dependenciaResponsable) {
        this.dependenciaResponsable = dependenciaResponsable;
    }

    public String getInstitucionCapacitadora() {
        return institucionCapacitadora;
    }

    public void setInstitucionCapacitadora(String institucionCapacitadora) {
        this.institucionCapacitadora = institucionCapacitadora;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public Aspirantes getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirantes idAspirante) {
        this.idAspirante = idAspirante;
    }

    public CatEficiencia getIdEficiencia() {
        return idEficiencia;
    }

    public void setIdEficiencia(CatEficiencia idEficiencia) {
        this.idEficiencia = idEficiencia;
    }

    public CatNivelCurso getIdNivelCurso() {
        return idNivelCurso;
    }

    public void setIdNivelCurso(CatNivelCurso idNivelCurso) {
        this.idNivelCurso = idNivelCurso;
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
        hash += (idPlantel != null ? idPlantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantel)) {
            return false;
        }
        Plantel other = (Plantel) object;
        if ((this.idPlantel == null && other.idPlantel != null) || (this.idPlantel != null && !this.idPlantel.equals(other.idPlantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Plantel[ idPlantel=" + idPlantel + " ]";
    }
    
}
