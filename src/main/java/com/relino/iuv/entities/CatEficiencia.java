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
@Table(name = "CAT_EFICIENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEficiencia.findAll", query = "SELECT c FROM CatEficiencia c")
    , @NamedQuery(name = "CatEficiencia.findByIdEficiencia", query = "SELECT c FROM CatEficiencia c WHERE c.idEficiencia = :idEficiencia")
    , @NamedQuery(name = "CatEficiencia.findByEficiencia", query = "SELECT c FROM CatEficiencia c WHERE c.eficiencia = :eficiencia")
    , @NamedQuery(name = "CatEficiencia.findByEstatus", query = "SELECT c FROM CatEficiencia c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatEficiencia.findByOrden", query = "SELECT c FROM CatEficiencia c WHERE c.orden = :orden")})
public class CatEficiencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EFICIENCIA")
    private Integer idEficiencia;
    @Size(max = 255)
    @Column(name = "EFICIENCIA")
    private String eficiencia;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idEficiencia")
    private List<Plantel> plantelList;

    public CatEficiencia() {
    }

    public CatEficiencia(Integer idEficiencia) {
        this.idEficiencia = idEficiencia;
    }

    public Integer getIdEficiencia() {
        return idEficiencia;
    }

    public void setIdEficiencia(Integer idEficiencia) {
        this.idEficiencia = idEficiencia;
    }

    public String getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(String eficiencia) {
        this.eficiencia = eficiencia;
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
    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEficiencia != null ? idEficiencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEficiencia)) {
            return false;
        }
        CatEficiencia other = (CatEficiencia) object;
        if ((this.idEficiencia == null && other.idEficiencia != null) || (this.idEficiencia != null && !this.idEficiencia.equals(other.idEficiencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEficiencia();
    }
    
}
