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
@Table(name = "CAT_MOV_BITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatMovBita.findAll", query = "SELECT c FROM CatMovBita c")
    , @NamedQuery(name = "CatMovBita.findByIdMovBita", query = "SELECT c FROM CatMovBita c WHERE c.idMovBita = :idMovBita")
    , @NamedQuery(name = "CatMovBita.findByMovimiento", query = "SELECT c FROM CatMovBita c WHERE c.movimiento = :movimiento")})
public class CatMovBita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MOV_BITA")
    private Integer idMovBita;
    @Size(max = 255)
    @Column(name = "MOVIMIENTO")
    private String movimiento;
    @OneToMany(mappedBy = "idMovimiento")
    private List<Bitacora> bitacoraList;

    public CatMovBita() {
    }

    public CatMovBita(Integer idMovBita) {
        this.idMovBita = idMovBita;
    }

    public Integer getIdMovBita() {
        return idMovBita;
    }

    public void setIdMovBita(Integer idMovBita) {
        this.idMovBita = idMovBita;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    @XmlTransient
    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

    public void setBitacoraList(List<Bitacora> bitacoraList) {
        this.bitacoraList = bitacoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovBita != null ? idMovBita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMovBita)) {
            return false;
        }
        CatMovBita other = (CatMovBita) object;
        if ((this.idMovBita == null && other.idMovBita != null) || (this.idMovBita != null && !this.idMovBita.equals(other.idMovBita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.CatMovBita[ idMovBita=" + idMovBita + " ]";
    }
    
}
