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
@Table(name = "CAT_ESTADOS_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatEstadosPais.findAll", query = "SELECT c FROM CatEstadosPais c")
    , @NamedQuery(name = "CatEstadosPais.findByIdEstadoPais", query = "SELECT c FROM CatEstadosPais c WHERE c.idEstadoPais = :idEstadoPais")
    , @NamedQuery(name = "CatEstadosPais.findByClaveEstadoPais", query = "SELECT c FROM CatEstadosPais c WHERE c.claveEstadoPais = :claveEstadoPais")
    , @NamedQuery(name = "CatEstadosPais.findByEstadoPais", query = "SELECT c FROM CatEstadosPais c WHERE c.estadoPais = :estadoPais")})
public class CatEstadosPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_PAIS")
    private Integer idEstadoPais;
    @Column(name = "CLAVE_ESTADO_PAIS")
    private Integer claveEstadoPais;
    @Size(max = 255)
    @Column(name = "ESTADO_PAIS")
    private String estadoPais;
    @OneToMany(mappedBy = "idEntidadNacimiento")
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idEstadoPais")
    private List<CatMunicipioPais> catMunicipioPaisList;
    @OneToMany(mappedBy = "idEntidad")
    private List<DomicilioAspirante> domicilioAspiranteList;
    @OneToMany(mappedBy = "idEntidad")
    private List<Referencias> referenciasList;
    @Size(max = 255)
    @Column(name = "ABREVIATURA")
    private String abreviatura;    
    @Column(name = "ORDEN")
    private Integer orden;
    
    public CatEstadosPais() {
    }

    public CatEstadosPais(Integer idEstadoPais) {
        this.idEstadoPais = idEstadoPais;
    }

    public Integer getIdEstadoPais() {
        return idEstadoPais;
    }

    public void setIdEstadoPais(Integer idEstadoPais) {
        this.idEstadoPais = idEstadoPais;
    }

    public Integer getClaveEstadoPais() {
        return claveEstadoPais;
    }

    public void setClaveEstadoPais(Integer claveEstadoPais) {
        this.claveEstadoPais = claveEstadoPais;
    }

    public String getEstadoPais() {
        return estadoPais;
    }

    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<CatMunicipioPais> getCatMunicipioPaisList() {
        return catMunicipioPaisList;
    }

    public void setCatMunicipioPaisList(List<CatMunicipioPais> catMunicipioPaisList) {
        this.catMunicipioPaisList = catMunicipioPaisList;
    }

    @XmlTransient
    public List<DomicilioAspirante> getDomicilioAspiranteList() {
        return domicilioAspiranteList;
    }

    public void setDomicilioAspiranteList(List<DomicilioAspirante> domicilioAspiranteList) {
        this.domicilioAspiranteList = domicilioAspiranteList;
    }

    @XmlTransient
    public List<Referencias> getReferenciasList() {
        return referenciasList;
    }

    public void setReferenciasList(List<Referencias> referenciasList) {
        this.referenciasList = referenciasList;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPais != null ? idEstadoPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEstadosPais)) {
            return false;
        }
        CatEstadosPais other = (CatEstadosPais) object;
        if ((this.idEstadoPais == null && other.idEstadoPais != null) || (this.idEstadoPais != null && !this.idEstadoPais.equals(other.idEstadoPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getEstadoPais();
    }
    
}
