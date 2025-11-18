/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CAT_TIPO_ANEXOS_PASP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatTipoAnexosPasp.findAll", query = "SELECT c FROM CatTipoAnexosPasp c")
    , @NamedQuery(name = "CatTipoAnexosPasp.findByIdTipoAnexosPasp", query = "SELECT c FROM CatTipoAnexosPasp c WHERE c.idTipoAnexosPasp = :idTipoAnexosPasp")
    , @NamedQuery(name = "CatTipoAnexosPasp.findByTipoAnexosPasp", query = "SELECT c FROM CatTipoAnexosPasp c WHERE c.tipoAnexosPasp = :tipoAnexosPasp")
    , @NamedQuery(name = "CatTipoAnexosPasp.findByOrden", query = "SELECT c FROM CatTipoAnexosPasp c WHERE c.orden = :orden")
    , @NamedQuery(name = "CatTipoAnexosPasp.findByEstatus", query = "SELECT c FROM CatTipoAnexosPasp c WHERE c.estatus = :estatus")})
public class CatTipoAnexosPasp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_ANEXOS_PASP")
    private BigDecimal idTipoAnexosPasp;
    @Size(max = 255)
    @Column(name = "TIPO_ANEXOS_PASP")
    private String tipoAnexosPasp;
    @Column(name = "ORDEN")
    private Integer orden;
    @Column(name = "ESTATUS")
    private Integer estatus;
    @OneToMany(mappedBy = "idTipoAnexosPasp")
    private List<AnexoPaspirante> anexoPaspiranteList;

    public CatTipoAnexosPasp() {
    }

    public CatTipoAnexosPasp(BigDecimal idTipoAnexosPasp) {
        this.idTipoAnexosPasp = idTipoAnexosPasp;
    }

    public BigDecimal getIdTipoAnexosPasp() {
        return idTipoAnexosPasp;
    }

    public void setIdTipoAnexosPasp(BigDecimal idTipoAnexosPasp) {
        this.idTipoAnexosPasp = idTipoAnexosPasp;
    }

    public String getTipoAnexosPasp() {
        return tipoAnexosPasp;
    }

    public void setTipoAnexosPasp(String tipoAnexosPasp) {
        this.tipoAnexosPasp = tipoAnexosPasp;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public List<AnexoPaspirante> getAnexoPaspiranteList() {
        return anexoPaspiranteList;
    }

    public void setAnexoPaspiranteList(List<AnexoPaspirante> anexoPaspiranteList) {
        this.anexoPaspiranteList = anexoPaspiranteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAnexosPasp != null ? idTipoAnexosPasp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTipoAnexosPasp)) {
            return false;
        }
        CatTipoAnexosPasp other = (CatTipoAnexosPasp) object;
        if ((this.idTipoAnexosPasp == null && other.idTipoAnexosPasp != null) || (this.idTipoAnexosPasp != null && !this.idTipoAnexosPasp.equals(other.idTipoAnexosPasp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatTipoAnexosPasp[ idTipoAnexosPasp=" + idTipoAnexosPasp + " ]";
    }
    
}
