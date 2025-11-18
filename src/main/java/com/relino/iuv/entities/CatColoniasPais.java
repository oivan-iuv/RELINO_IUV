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
@Table(name = "CAT_COLONIAS_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatColoniasPais.findAll", query = "SELECT c FROM CatColoniasPais c")
    , @NamedQuery(name = "CatColoniasPais.findByIdColoniaPais", query = "SELECT c FROM CatColoniasPais c WHERE c.idColoniaPais = :idColoniaPais")
    , @NamedQuery(name = "CatColoniasPais.findByClaveMunicipioPais", query = "SELECT c FROM CatColoniasPais c WHERE c.claveMunicipioPais = :claveMunicipioPais AND c.claveEstadoPais=:claveEstadoPais ORDER BY c.coloniaPais")
    , @NamedQuery(name = "CatColoniasPais.findByColoniaPais", query = "SELECT c FROM CatColoniasPais c WHERE c.coloniaPais = :coloniaPais")
    , @NamedQuery(name = "CatColoniasPais.findByCodigoPostalPais", query = "SELECT c FROM CatColoniasPais c WHERE c.codigoPostalPais = :codigoPostalPais")
    , @NamedQuery(name = "CatColoniasPais.findByClaveEstadoPais", query = "SELECT c FROM CatColoniasPais c WHERE c.claveEstadoPais = :claveEstadoPais")})
public class CatColoniasPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLONIA_PAIS")
    private Integer idColoniaPais;
    @Column(name = "CLAVE_MUNICIPIO_PAIS")
    private Integer claveMunicipioPais;
    @Size(max = 255)
    @Column(name = "COLONIA_PAIS")
    private String coloniaPais;
    @Size(max = 255)
    @Column(name = "CODIGO_POSTAL_PAIS")
    private String codigoPostalPais;
    @Column(name = "CLAVE_ESTADO_PAIS")
    private Integer claveEstadoPais;
    @OneToMany(mappedBy = "idColonia")
    private List<DomicilioAspirante> domicilioAspiranteList;
    @OneToMany(mappedBy = "idColonia")
    private List<Referencias> referenciasList;

    public CatColoniasPais() {
    }

    public CatColoniasPais(Integer idColoniaPais) {
        this.idColoniaPais = idColoniaPais;
    }

    public Integer getIdColoniaPais() {
        return idColoniaPais;
    }

    public void setIdColoniaPais(Integer idColoniaPais) {
        this.idColoniaPais = idColoniaPais;
    }

    public Integer getClaveMunicipioPais() {
        return claveMunicipioPais;
    }

    public void setClaveMunicipioPais(Integer claveMunicipioPais) {
        this.claveMunicipioPais = claveMunicipioPais;
    }

    public String getColoniaPais() {
        return coloniaPais;
    }

    public void setColoniaPais(String coloniaPais) {
        this.coloniaPais = coloniaPais;
    }

    public String getCodigoPostalPais() {
        return codigoPostalPais;
    }

    public void setCodigoPostalPais(String codigoPostalPais) {
        this.codigoPostalPais = codigoPostalPais;
    }

    public Integer getClaveEstadoPais() {
        return claveEstadoPais;
    }

    public void setClaveEstadoPais(Integer claveEstadoPais) {
        this.claveEstadoPais = claveEstadoPais;
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
        hash += (idColoniaPais != null ? idColoniaPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatColoniasPais)) {
            return false;
        }
        CatColoniasPais other = (CatColoniasPais) object;
        if ((this.idColoniaPais == null && other.idColoniaPais != null) || (this.idColoniaPais != null && !this.idColoniaPais.equals(other.idColoniaPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getColoniaPais();
    }
    
}
