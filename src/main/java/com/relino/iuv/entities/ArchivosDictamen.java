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
import javax.persistence.Lob;
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
@Table(name = "ARCHIVOS_DICTAMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivosDictamen.findAll", query = "SELECT a FROM ArchivosDictamen a")
    , @NamedQuery(name = "ArchivosDictamen.findByIdArchivoDictamen", query = "SELECT a FROM ArchivosDictamen a WHERE a.idArchivoDictamen = :idArchivoDictamen")
    , @NamedQuery(name = "ArchivosDictamen.findByIdAspirante", query = "SELECT a FROM ArchivosDictamen a WHERE a.idAspirante = :idAspirante")
    , @NamedQuery(name = "ArchivosDictamen.findByTipoArchivo", query = "SELECT a FROM ArchivosDictamen a WHERE a.tipoArchivo = :tipoArchivo")
    , @NamedQuery(name = "ArchivosDictamen.findByTitulo", query = "SELECT a FROM ArchivosDictamen a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "ArchivosDictamen.findByFechaCreacion", query = "SELECT a FROM ArchivosDictamen a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ArchivosDictamen.findByFechaModificacion", query = "SELECT a FROM ArchivosDictamen a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "ArchivosDictamen.findByUuidNameImagen", query = "SELECT a FROM ArchivosDictamen a WHERE a.uuidNameImagen = :uuidNameImagen")
    , @NamedQuery(name = "ArchivosDictamen.findByExtensionArchivo", query = "SELECT a FROM ArchivosDictamen a WHERE a.extensionArchivo = :extensionArchivo")})
public class ArchivosDictamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARCHIVO_DICTAMEN")
    private Integer idArchivoDictamen;
    @Column(name = "ID_ASPIRANTE")
    private Integer idAspirante;
    @Lob
    @Column(name = "ARCHIVO_BYTES")
    private Serializable archivoBytes;
    @Size(max = 255)
    @Column(name = "TIPO_ARCHIVO")
    private String tipoArchivo;
    @Size(max = 255)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 255)
    @Column(name = "UUID_NAME_IMAGEN")
    private String uuidNameImagen;
    @Size(max = 25)
    @Column(name = "EXTENSION_ARCHIVO")
    private String extensionArchivo;
    @JoinColumn(name = "ID_DICTAMEN_ASPIRANTE", referencedColumnName = "ID_DICTAMEN_APIRANTE")
    @ManyToOne
    private DictamenAspirante idDictamenAspirante;

    public ArchivosDictamen() {
    }

    public ArchivosDictamen(Integer idArchivoDictamen) {
        this.idArchivoDictamen = idArchivoDictamen;
    }

    public Integer getIdArchivoDictamen() {
        return idArchivoDictamen;
    }

    public void setIdArchivoDictamen(Integer idArchivoDictamen) {
        this.idArchivoDictamen = idArchivoDictamen;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Serializable getArchivoBytes() {
        return archivoBytes;
    }

    public void setArchivoBytes(Serializable archivoBytes) {
        this.archivoBytes = archivoBytes;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUuidNameImagen() {
        return uuidNameImagen;
    }

    public void setUuidNameImagen(String uuidNameImagen) {
        this.uuidNameImagen = uuidNameImagen;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public DictamenAspirante getIdDictamenAspirante() {
        return idDictamenAspirante;
    }

    public void setIdDictamenAspirante(DictamenAspirante idDictamenAspirante) {
        this.idDictamenAspirante = idDictamenAspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivoDictamen != null ? idArchivoDictamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchivosDictamen)) {
            return false;
        }
        ArchivosDictamen other = (ArchivosDictamen) object;
        if ((this.idArchivoDictamen == null && other.idArchivoDictamen != null) || (this.idArchivoDictamen != null && !this.idArchivoDictamen.equals(other.idArchivoDictamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.ArchivosDictamen[ idArchivoDictamen=" + idArchivoDictamen + " ]";
    }
    
}
