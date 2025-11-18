/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "ENCABEZADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encabezados.findAll", query = "SELECT e FROM Encabezados e")
    , @NamedQuery(name = "Encabezados.findByIdEncabezado", query = "SELECT e FROM Encabezados e WHERE e.idEncabezado = :idEncabezado")
    , @NamedQuery(name = "Encabezados.findByYear", query = "SELECT e FROM Encabezados e WHERE e.year = :year")
    , @NamedQuery(name = "Encabezados.findByLeyenda", query = "SELECT e FROM Encabezados e WHERE e.leyenda = :leyenda")
    , @NamedQuery(name = "Encabezados.findByDescripcion", query = "SELECT e FROM Encabezados e WHERE e.descripcion = :descripcion")})
public class Encabezados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ENCABEZADO")
    private Integer idEncabezado;
    @Column(name = "YEAR")
    private Integer year;
    @Size(max = 100)
    @Column(name = "LEYENDA")
    private String leyenda;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Lob
    @Column(name = "LOGOTIPO")
    private byte[] logotipo;
    @Lob
    @Column(name = "INSTITUCION")
    private byte[] institucion;
    @Lob
    @Column(name = "PIE")
    private byte[] pie;

    public Encabezados() {
    }

    public Encabezados(Integer idEncabezado) {
        this.idEncabezado = idEncabezado;
    }

    public Integer getIdEncabezado() {
        return idEncabezado;
    }

    public void setIdEncabezado(Integer idEncabezado) {
        this.idEncabezado = idEncabezado;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(byte[] logotipo) {
        this.logotipo = logotipo;
    }

    public byte[] getInstitucion() {
        return institucion;
    }

    public void setInstitucion(byte[] institucion) {
        this.institucion = institucion;
    }

    public byte[] getPie() {
        return pie;
    }

    public void setPie(byte[] pie) {
        this.pie = pie;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncabezado != null ? idEncabezado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encabezados)) {
            return false;
        }
        Encabezados other = (Encabezados) object;
        if ((this.idEncabezado == null && other.idEncabezado != null) || (this.idEncabezado != null && !this.idEncabezado.equals(other.idEncabezado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Encabezados[ idEncabezado=" + idEncabezado + " ]";
    }
    
}
