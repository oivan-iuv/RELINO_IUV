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
@Table(name = "CAT_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatParametros.findAll", query = "SELECT c FROM CatParametros c")
    , @NamedQuery(name = "CatParametros.findByIdParametro", query = "SELECT c FROM CatParametros c WHERE c.idParametro = :idParametro")
    , @NamedQuery(name = "CatParametros.findByNumeroParametro", query = "SELECT c FROM CatParametros c WHERE c.numeroParametro = :numeroParametro")
    , @NamedQuery(name = "CatParametros.findByVariableParametro", query = "SELECT c FROM CatParametros c WHERE c.variableParametro = :variableParametro")
    , @NamedQuery(name = "CatParametros.findByDescripcionParametro", query = "SELECT c FROM CatParametros c WHERE c.descripcionParametro = :descripcionParametro")
    , @NamedQuery(name = "CatParametros.findByOrden", query = "SELECT c FROM CatParametros c WHERE c.orden = :orden")})
public class CatParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO")
    private Integer idParametro;
    @Column(name = "NUMERO_PARAMETRO")
    private Integer numeroParametro;
    @Size(max = 500)
    @Column(name = "VARIABLE_PARAMETRO")
    private String variableParametro;
    @Size(max = 500)
    @Column(name = "DESCRIPCION_PARAMETRO")
    private String descripcionParametro;
    @Column(name = "ORDEN")
    private Integer orden;

    public CatParametros() {
    }

    public CatParametros(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Integer getNumeroParametro() {
        return numeroParametro;
    }

    public void setNumeroParametro(Integer numeroParametro) {
        this.numeroParametro = numeroParametro;
    }

    public String getVariableParametro() {
        return variableParametro;
    }

    public void setVariableParametro(String variableParametro) {
        this.variableParametro = variableParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
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
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatParametros)) {
            return false;
        }
        CatParametros other = (CatParametros) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatParametros[ idParametro=" + idParametro + " ]";
    }
    
}
