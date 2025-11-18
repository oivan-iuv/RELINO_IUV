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
@Table(name = "CAT_NIVEL_CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatNivelCurso.findAll", query = "SELECT c FROM CatNivelCurso c")
    , @NamedQuery(name = "CatNivelCurso.findByIdNivelCurso", query = "SELECT c FROM CatNivelCurso c WHERE c.idNivelCurso = :idNivelCurso")
    , @NamedQuery(name = "CatNivelCurso.findByNivelCurso", query = "SELECT c FROM CatNivelCurso c WHERE c.nivelCurso = :nivelCurso")
    , @NamedQuery(name = "CatNivelCurso.findByEstatus", query = "SELECT c FROM CatNivelCurso c WHERE c.estatus = :estatus")
    , @NamedQuery(name = "CatNivelCurso.findByOrden", query = "SELECT c FROM CatNivelCurso c WHERE c.orden = :orden")})
public class CatNivelCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NIVEL_CURSO")
    private Integer idNivelCurso;
    @Size(max = 255)
    @Column(name = "NIVEL_CURSO")
    private String nivelCurso;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(mappedBy = "idNivelCurso")
    private List<Plantel> plantelList;

    public CatNivelCurso() {
    }

    public CatNivelCurso(Integer idNivelCurso) {
        this.idNivelCurso = idNivelCurso;
    }

    public Integer getIdNivelCurso() {
        return idNivelCurso;
    }

    public void setIdNivelCurso(Integer idNivelCurso) {
        this.idNivelCurso = idNivelCurso;
    }

    public String getNivelCurso() {
        return nivelCurso;
    }

    public void setNivelCurso(String nivelCurso) {
        this.nivelCurso = nivelCurso;
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
        hash += (idNivelCurso != null ? idNivelCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatNivelCurso)) {
            return false;
        }
        CatNivelCurso other = (CatNivelCurso) object;
        if ((this.idNivelCurso == null && other.idNivelCurso != null) || (this.idNivelCurso != null && !this.idNivelCurso.equals(other.idNivelCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNivelCurso();
    }
    
}
