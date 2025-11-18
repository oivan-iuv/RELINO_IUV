/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "BITACORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b")
    , @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora")
    , @NamedQuery(name = "Bitacora.findByIpUsuario", query = "SELECT b FROM Bitacora b WHERE b.ipUsuario = :ipUsuario")
    , @NamedQuery(name = "Bitacora.findByDescripcion", query = "SELECT b FROM Bitacora b WHERE b.descripcion = :descripcion")
    , @NamedQuery(name = "Bitacora.findByFecBitacora", query = "SELECT b FROM Bitacora b WHERE b.fecBitacora = :fecBitacora")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BITACORA")
    private Integer idBitacora;
    @Size(max = 25)
    @Column(name = "IP_USUARIO")
    private String ipUsuario;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FEC_BITACORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecBitacora;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID_MODULO")
    @ManyToOne
    private CatModulos idModulo;
    @JoinColumn(name = "ID_MOVIMIENTO", referencedColumnName = "ID_MOV_BITA")
    @ManyToOne
    private CatMovBita idMovimiento;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuario;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecBitacora() {
        return fecBitacora;
    }

    public void setFecBitacora(Date fecBitacora) {
        this.fecBitacora = fecBitacora;
    }

    public CatModulos getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(CatModulos idModulo) {
        this.idModulo = idModulo;
    }

    public CatMovBita getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(CatMovBita idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
