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
@Table(name = "CAT_ESTADO_CIVIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEstadoCivil.findAll", query = "SELECT c FROM CatEstadoCivil c")
    , @NamedQuery(name = "CatEstadoCivil.findByIdEstadoCivil", query = "SELECT c FROM CatEstadoCivil c WHERE c.idEstadoCivil = :idEstadoCivil")
    , @NamedQuery(name = "CatEstadoCivil.findByEstadoCivil", query = "SELECT c FROM CatEstadoCivil c WHERE c.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "CatEstadoCivil.findByEstatus", query = "SELECT c FROM CatEstadoCivil c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatEstadoCivil.findByOrden", query = "SELECT c FROM CatEstadoCivil c WHERE c.orden = :orden")})
public class CatEstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_CIVIL")
    private Integer idEstadoCivil;
    @Size(max = 255)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idEstadoCivil")
    private List<Aspirantes> aspirantesList;

    public CatEstadoCivil() {
    }

    public CatEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCivil != null ? idEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstadoCivil)) {
            return false;
        }
        CatEstadoCivil other = (CatEstadoCivil) object;
        if ((this.idEstadoCivil == null && other.idEstadoCivil != null) || (this.idEstadoCivil != null && !this.idEstadoCivil.equals(other.idEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEstadoCivil();
    }
    
}
