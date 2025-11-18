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
@Table(name = "CAT_AREAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatAreas.findAll", query = "SELECT c FROM CatAreas c")
    , @NamedQuery(name = "CatAreas.findByIdArea", query = "SELECT c FROM CatAreas c WHERE c.idArea = :idArea")
    , @NamedQuery(name = "CatAreas.findByArea", query = "SELECT c FROM CatAreas c WHERE c.area = :area")
    , @NamedQuery(name = "CatAreas.findByIdDependencia", query = "SELECT c FROM CatAreas c WHERE c.idDependencia = :idDependencia")
    , @NamedQuery(name = "CatAreas.findByEstatus", query = "SELECT c FROM CatAreas c WHERE c.estatus = :estatus")})
public class CatAreas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_AREA")
    private Integer idArea;
    @Size(max = 255)
    @Column(name = "AREA")
    private String area;
    @Column(name = "ID_DEPENDENCIA")
    private Integer idDependencia;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @OneToMany(mappedBy = "idArea")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idArea")
    private List<ActivacionNominal> activacionNominalList;

    public CatAreas() {
    }

    public CatAreas(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<ActivacionNominal> getActivacionNominalList() {
        return activacionNominalList;
    }

    public void setActivacionNominalList(List<ActivacionNominal> activacionNominalList) {
        this.activacionNominalList = activacionNominalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatAreas)) {
            return false;
        }
        CatAreas other = (CatAreas) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatAreas[ idArea=" + idArea + " ]";
    }
    
}
