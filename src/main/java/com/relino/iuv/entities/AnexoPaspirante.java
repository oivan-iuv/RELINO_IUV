/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ANEXO_PASPIRANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnexoPaspirante.findAll", query = "SELECT a FROM AnexoPaspirante a")
    , @NamedQuery(name = "AnexoPaspirante.findByIdAnexoPaspirante", query = "SELECT a FROM AnexoPaspirante a WHERE a.idAnexoPaspirante = :idAnexoPaspirante")
    , @NamedQuery(name = "AnexoPaspirante.findByTipoArchivo", query = "SELECT a FROM AnexoPaspirante a WHERE a.tipoArchivo = :tipoArchivo")
    , @NamedQuery(name = "AnexoPaspirante.findByNombreArchivo", query = "SELECT a FROM AnexoPaspirante a WHERE a.nombreArchivo = :nombreArchivo")
    , @NamedQuery(name = "AnexoPaspirante.findByUuidName", query = "SELECT a FROM AnexoPaspirante a WHERE a.uuidName = :uuidName")
    , @NamedQuery(name = "AnexoPaspirante.findByFecCreacion", query = "SELECT a FROM AnexoPaspirante a WHERE a.fecCreacion = :fecCreacion")
    , @NamedQuery(name = "AnexoPaspirante.findByFecModif", query = "SELECT a FROM AnexoPaspirante a WHERE a.fecModif = :fecModif")})
public class AnexoPaspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ANEXO_PASPIRANTE")
    private BigDecimal idAnexoPaspirante;
    @Size(max = 255)
    @Column(name = "TIPO_ARCHIVO")
    private String tipoArchivo;
    @Size(max = 255)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Size(max = 255)
    @Column(name = "UUID_NAME")
    private String uuidName;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Column(name = "FEC_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecModif;
    @JoinTable(name = "PROF_ASPIRANTES_ANEXOS", joinColumns = {
        @JoinColumn(name = "ID_ANEXO_PASPIRANTE", referencedColumnName = "ID_ANEXO_PASPIRANTE")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PROF_ASPIRANTE", referencedColumnName = "ID_PROF_ASPIRANTES")})
    @ManyToMany
    private List<ProfAspirantes> profAspirantesList;
    @JoinColumn(name = "ID_TIPO_ANEXOS_PASP", referencedColumnName = "ID_TIPO_ANEXOS_PASP")
    @ManyToOne
    private CatTipoAnexosPasp idTipoAnexosPasp;

    public AnexoPaspirante() {
    }

    public AnexoPaspirante(BigDecimal idAnexoPaspirante) {
        this.idAnexoPaspirante = idAnexoPaspirante;
    }

    public BigDecimal getIdAnexoPaspirante() {
        return idAnexoPaspirante;
    }

    public void setIdAnexoPaspirante(BigDecimal idAnexoPaspirante) {
        this.idAnexoPaspirante = idAnexoPaspirante;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
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

    @XmlTransient
    public List<ProfAspirantes> getProfAspirantesList() {
        return profAspirantesList;
    }

    public void setProfAspirantesList(List<ProfAspirantes> profAspirantesList) {
        this.profAspirantesList = profAspirantesList;
    }

    public CatTipoAnexosPasp getIdTipoAnexosPasp() {
        return idTipoAnexosPasp;
    }

    public void setIdTipoAnexosPasp(CatTipoAnexosPasp idTipoAnexosPasp) {
        this.idTipoAnexosPasp = idTipoAnexosPasp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnexoPaspirante != null ? idAnexoPaspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnexoPaspirante)) {
            return false;
        }
        AnexoPaspirante other = (AnexoPaspirante) object;
        if ((this.idAnexoPaspirante == null && other.idAnexoPaspirante != null) || (this.idAnexoPaspirante != null && !this.idAnexoPaspirante.equals(other.idAnexoPaspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.AnexoPaspirante[ idAnexoPaspirante=" + idAnexoPaspirante + " ]";
    }
    
}
