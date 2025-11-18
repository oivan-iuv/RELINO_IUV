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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAT_MUNICIPIO_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatMunicipioPais.findAll", query = "SELECT c FROM CatMunicipioPais c")
    , @NamedQuery(name = "CatMunicipioPais.findByIdMunicipioPais", query = "SELECT c FROM CatMunicipioPais c WHERE c.idMunicipioPais = :idMunicipioPais")
    , @NamedQuery(name = "CatMunicipioPais.findByClaveMunicipioPais", query = "SELECT c FROM CatMunicipioPais c WHERE c.claveMunicipioPais = :claveMunicipioPais")
    , @NamedQuery(name = "CatMunicipioPais.findByMunicipioPais", query = "SELECT c FROM CatMunicipioPais c WHERE c.municipioPais = :municipioPais")})
public class CatMunicipioPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MUNICIPIO_PAIS")
    private Integer idMunicipioPais;
    @Column(name = "CLAVE_MUNICIPIO_PAIS")
    private Integer claveMunicipioPais;
    @Size(max = 255)
    @Column(name = "MUNICIPIO_PAIS")
    private String municipioPais;
    @OneToMany(mappedBy = "idMunicipioNacimiento")
    private List<Aspirantes> aspirantesList;
    @JoinColumn(name = "ID_ESTADO_PAIS", referencedColumnName = "ID_ESTADO_PAIS")
    @ManyToOne
    private CatEstadosPais idEstadoPais;
    @OneToMany(mappedBy = "idMunicipio")
    private List<DomicilioAspirante> domicilioAspiranteList;
    @OneToMany(mappedBy = "idMunicipio")
    private List<Referencias> referenciasList;

    public CatMunicipioPais() {
    }

    public CatMunicipioPais(Integer idMunicipioPais) {
        this.idMunicipioPais = idMunicipioPais;
    }

    public Integer getIdMunicipioPais() {
        return idMunicipioPais;
    }

    public void setIdMunicipioPais(Integer idMunicipioPais) {
        this.idMunicipioPais = idMunicipioPais;
    }

    public Integer getClaveMunicipioPais() {
        return claveMunicipioPais;
    }

    public void setClaveMunicipioPais(Integer claveMunicipioPais) {
        this.claveMunicipioPais = claveMunicipioPais;
    }

    public String getMunicipioPais() {
        return municipioPais;
    }

    public void setMunicipioPais(String municipioPais) {
        this.municipioPais = municipioPais;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    public CatEstadosPais getIdEstadoPais() {
        return idEstadoPais;
    }

    public void setIdEstadoPais(CatEstadosPais idEstadoPais) {
        this.idEstadoPais = idEstadoPais;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipioPais != null ? idMunicipioPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMunicipioPais)) {
            return false;
        }
        CatMunicipioPais other = (CatMunicipioPais) object;
        if ((this.idMunicipioPais == null && other.idMunicipioPais != null) || (this.idMunicipioPais != null && !this.idMunicipioPais.equals(other.idMunicipioPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMunicipioPais();
    }
    
}
