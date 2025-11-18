/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "CAT_GENERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatGenero.findAll", query = "SELECT c FROM CatGenero c")
    , @NamedQuery(name = "CatGenero.findByIdGenero", query = "SELECT c FROM CatGenero c WHERE c.idGenero = :idGenero")
    , @NamedQuery(name = "CatGenero.findByGenero", query = "SELECT c FROM CatGenero c WHERE c.genero = :genero")
    , @NamedQuery(name = "CatGenero.findByEstatus", query = "SELECT c FROM CatGenero c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatGenero.findByOrden", query = "SELECT c FROM CatGenero c WHERE c.orden = :orden")})
public class CatGenero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GENERO")
    private Integer idGenero;
    @Size(max = 255)
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idSexo")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idSexo")
    private List<Referencias> referenciasList;

    public CatGenero() {
    }

    public CatGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<Referencias> getReferenciasList() {
        return referenciasList;
    }

    public void setReferenciasList(List<Referencias> referenciasList) {
        this.referenciasList = referenciasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatGenero)) {
            return false;
        }
        CatGenero other = (CatGenero) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getGenero();
    }
    
}
