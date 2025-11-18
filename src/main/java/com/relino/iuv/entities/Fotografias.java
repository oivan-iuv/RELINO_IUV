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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "FOTOGRAFIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotografias.findAll", query = "SELECT f FROM Fotografias f")
    , @NamedQuery(name = "Fotografias.findByIdFotografia", query = "SELECT f FROM Fotografias f WHERE f.idFotografia = :idFotografia")
    , @NamedQuery(name = "Fotografias.findByTitulo", query = "SELECT f FROM Fotografias f WHERE f.titulo = :titulo")
    , @NamedQuery(name = "Fotografias.findByUuidNameImagen", query = "SELECT f FROM Fotografias f WHERE f.uuidNameImagen = :uuidNameImagen")
    , @NamedQuery(name = "Fotografias.findByExtensionArchivo", query = "SELECT f FROM Fotografias f WHERE f.extensionArchivo = :extensionArchivo")
    , @NamedQuery(name = "Fotografias.findByFechaCreacion", query = "SELECT f FROM Fotografias f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Fotografias.findByFechaModificacion", query = "SELECT f FROM Fotografias f WHERE f.fechaModificacion = :fechaModificacion")})
public class Fotografias implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_FOTOGRAFIA")
    @SequenceGenerator(name = "SEC_FOTOGRAFIA", sequenceName = "SEC_FOTOGRAFIA", allocationSize = 1)
    @Column(name = "ID_FOTOGRAFIA")
    private Integer idFotografia;
    @Size(max = 255)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 255)
    @Column(name = "TIPO_ARCHIVO")
    private String tipoArchivo;
    @Size(max = 255)
    @Column(name = "UUID_NAME_IMAGEN")
    private String uuidNameImagen;
    @Size(max = 255)
    @Column(name = "EXTENSION_ARCHIVO")
    private String extensionArchivo;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")
    @ManyToOne
    private Aspirantes idAspirante;
    @Lob
    @Column(name = "ANEXO_BYTES")
    private byte[] anexoBytes;

    public Fotografias() {
    }

    public Fotografias(Integer idFotografia) {
        this.idFotografia = idFotografia;
    }

    public Integer getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Integer idFotografia) {
        this.idFotografia = idFotografia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
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

    public byte[] getAnexoBytes() {
        return anexoBytes;
    }

    public void setAnexoBytes(byte[] anexoBytes) {
        this.anexoBytes = anexoBytes;
    }

    public Aspirantes getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirantes idAspirante) {
        this.idAspirante = idAspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFotografia != null ? idFotografia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotografias)) {
            return false;
        }
        Fotografias other = (Fotografias) object;
        if ((this.idFotografia == null && other.idFotografia != null) || (this.idFotografia != null && !this.idFotografia.equals(other.idFotografia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getTitulo() + " " + getExtensionArchivo();
    }

}
