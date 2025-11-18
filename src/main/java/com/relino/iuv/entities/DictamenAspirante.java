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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "DICTAMEN_ASPIRANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DictamenAspirante.findAll", query = "SELECT d FROM DictamenAspirante d")
    , @NamedQuery(name = "DictamenAspirante.findByIdDictamenApirante", query = "SELECT d FROM DictamenAspirante d WHERE d.idDictamenApirante = :idDictamenApirante")
    , @NamedQuery(name = "DictamenAspirante.findByNoOficio", query = "SELECT d FROM DictamenAspirante d WHERE d.noOficio = :noOficio")
    , @NamedQuery(name = "DictamenAspirante.findByFechaOficio", query = "SELECT d FROM DictamenAspirante d WHERE d.fechaOficio = :fechaOficio")
    , @NamedQuery(name = "DictamenAspirante.findByObservaciones", query = "SELECT d FROM DictamenAspirante d WHERE d.observaciones = :observaciones")
    , @NamedQuery(name = "DictamenAspirante.findByFechaCaptura", query = "SELECT d FROM DictamenAspirante d WHERE d.fechaCaptura = :fechaCaptura")
    , @NamedQuery(name = "DictamenAspirante.findByFechaModificacion", query = "SELECT d FROM DictamenAspirante d WHERE d.fechaModificacion = :fechaModificacion")})
public class DictamenAspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DICTAMEN_APIRANTE")
    private Integer idDictamenApirante;
    @Size(max = 255)
    @Column(name = "NO_OFICIO")
    private String noOficio;
    @Column(name = "FECHA_OFICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOficio;
    @Size(max = 511)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")
    @ManyToOne
    private Aspirantes idAspirante;
    @JoinColumn(name = "ID_CAT_DICTAMEM_ASP", referencedColumnName = "ID_CAT_DICTAMEN_ASP")
    @ManyToOne
    private CatDictamenAspirante idCatDictamemAsp;
    @JoinColumn(name = "ID_SUB_BAJA", referencedColumnName = "ID_SUB_BAJA")
    @ManyToOne
    private CatSubBajas idSubBaja;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;
    @OneToMany(mappedBy = "idDictamenAspirante")
    private List<ArchivosDictamen> archivosDictamenList;

    public DictamenAspirante() {
    }

    public DictamenAspirante(Integer idDictamenApirante) {
        this.idDictamenApirante = idDictamenApirante;
    }

    public Integer getIdDictamenApirante() {
        return idDictamenApirante;
    }

    public void setIdDictamenApirante(Integer idDictamenApirante) {
        this.idDictamenApirante = idDictamenApirante;
    }

    public String getNoOficio() {
        return noOficio;
    }

    public void setNoOficio(String noOficio) {
        this.noOficio = noOficio;
    }

    public Date getFechaOficio() {
        return fechaOficio;
    }

    public void setFechaOficio(Date fechaOficio) {
        this.fechaOficio = fechaOficio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public CatDictamenAspirante getIdCatDictamemAsp() {
        return idCatDictamemAsp;
    }

    public void setIdCatDictamemAsp(CatDictamenAspirante idCatDictamemAsp) {
        this.idCatDictamemAsp = idCatDictamemAsp;
    }

    public CatSubBajas getIdSubBaja() {
        return idSubBaja;
    }

    public void setIdSubBaja(CatSubBajas idSubBaja) {
        this.idSubBaja = idSubBaja;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<ArchivosDictamen> getArchivosDictamenList() {
        return archivosDictamenList;
    }

    public void setArchivosDictamenList(List<ArchivosDictamen> archivosDictamenList) {
        this.archivosDictamenList = archivosDictamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDictamenApirante != null ? idDictamenApirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DictamenAspirante)) {
            return false;
        }
        DictamenAspirante other = (DictamenAspirante) object;
        if ((this.idDictamenApirante == null && other.idDictamenApirante != null) || (this.idDictamenApirante != null && !this.idDictamenApirante.equals(other.idDictamenApirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.DictamenAspirante[ idDictamenApirante=" + idDictamenApirante + " ]";
    }
    
}
