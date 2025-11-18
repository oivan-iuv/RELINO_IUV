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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IvanPC
 */
@Entity
@Table(name = "PROF_ASPIRANTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfAspirantes.findAll", query = "SELECT p FROM ProfAspirantes p")
    , @NamedQuery(name = "ProfAspirantes.findByIdProfAspirantes", query = "SELECT p FROM ProfAspirantes p WHERE p.idProfAspirantes = :idProfAspirantes")
    , @NamedQuery(name = "ProfAspirantes.findByReqDocumentales", query = "SELECT p FROM ProfAspirantes p WHERE p.reqDocumentales = :reqDocumentales")
    , @NamedQuery(name = "ProfAspirantes.findByPsicometrico", query = "SELECT p FROM ProfAspirantes p WHERE p.psicometrico = :psicometrico")
    , @NamedQuery(name = "ProfAspirantes.findByExamenMedico", query = "SELECT p FROM ProfAspirantes p WHERE p.examenMedico = :examenMedico")
    , @NamedQuery(name = "ProfAspirantes.findByConocimientosGrales", query = "SELECT p FROM ProfAspirantes p WHERE p.conocimientosGrales = :conocimientosGrales")
    , @NamedQuery(name = "ProfAspirantes.findByFisicoAtletico", query = "SELECT p FROM ProfAspirantes p WHERE p.fisicoAtletico = :fisicoAtletico")
    , @NamedQuery(name = "ProfAspirantes.findBySujetoViable", query = "SELECT p FROM ProfAspirantes p WHERE p.sujetoViable = :sujetoViable")})
public class ProfAspirantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROF_ASPIRANTES")
    private Integer idProfAspirantes;
    @Column(name = "REQ_DOCUMENTALES")
    private Integer reqDocumentales;
    @Column(name = "PSICOMETRICO")
    private Integer psicometrico;
    @Column(name = "EXAMEN_MEDICO")
    private Integer examenMedico;
    @Column(name = "CONOCIMIENTOS_GRALES")
    private Integer conocimientosGrales;
    @Column(name = "FISICO_ATLETICO")
    private Integer fisicoAtletico;
    @Column(name = "SUJETO_VIABLE")
    private Integer sujetoViable;
    @ManyToMany(mappedBy = "profAspirantesList")
    private List<AnexoPaspirante> anexoPaspiranteList;
    @JoinColumn(name = "ID_ASPIRANTE", referencedColumnName = "ID_ASPIRANTE")
    @ManyToOne
    private Aspirantes idAspirante;

    public ProfAspirantes() {
    }

    public ProfAspirantes(Integer idProfAspirantes) {
        this.idProfAspirantes = idProfAspirantes;
    }

    public Integer getIdProfAspirantes() {
        return idProfAspirantes;
    }

    public void setIdProfAspirantes(Integer idProfAspirantes) {
        this.idProfAspirantes = idProfAspirantes;
    }

    public Integer getReqDocumentales() {
        return reqDocumentales;
    }

    public void setReqDocumentales(Integer reqDocumentales) {
        this.reqDocumentales = reqDocumentales;
    }

    public Integer getPsicometrico() {
        return psicometrico;
    }

    public void setPsicometrico(Integer psicometrico) {
        this.psicometrico = psicometrico;
    }

    public Integer getExamenMedico() {
        return examenMedico;
    }

    public void setExamenMedico(Integer examenMedico) {
        this.examenMedico = examenMedico;
    }

    public Integer getConocimientosGrales() {
        return conocimientosGrales;
    }

    public void setConocimientosGrales(Integer conocimientosGrales) {
        this.conocimientosGrales = conocimientosGrales;
    }

    public Integer getFisicoAtletico() {
        return fisicoAtletico;
    }

    public void setFisicoAtletico(Integer fisicoAtletico) {
        this.fisicoAtletico = fisicoAtletico;
    }

    public Integer getSujetoViable() {
        return sujetoViable;
    }

    public void setSujetoViable(Integer sujetoViable) {
        this.sujetoViable = sujetoViable;
    }

    @XmlTransient
    public List<AnexoPaspirante> getAnexoPaspiranteList() {
        return anexoPaspiranteList;
    }

    public void setAnexoPaspiranteList(List<AnexoPaspirante> anexoPaspiranteList) {
        this.anexoPaspiranteList = anexoPaspiranteList;
    }

    public Aspirantes getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirantes idAspirante) {
        this.idAspirante = idAspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfAspirantes != null ? idProfAspirantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfAspirantes)) {
            return false;
        }
        ProfAspirantes other = (ProfAspirantes) object;
        if ((this.idProfAspirantes == null && other.idProfAspirantes != null) || (this.idProfAspirantes != null && !this.idProfAspirantes.equals(other.idProfAspirantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.ProfAspirantes[ idProfAspirantes=" + idProfAspirantes + " ]";
    }
    
}
