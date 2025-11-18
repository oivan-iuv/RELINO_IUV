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
@Table(name = "NOTIFICACION_MENSAJES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionMensajes.findAll", query = "SELECT n FROM NotificacionMensajes n")
    , @NamedQuery(name = "NotificacionMensajes.findByIdNotificacion", query = "SELECT n FROM NotificacionMensajes n WHERE n.idNotificacion = :idNotificacion")
    , @NamedQuery(name = "NotificacionMensajes.findByDescrNotificacion", query = "SELECT n FROM NotificacionMensajes n WHERE n.descrNotificacion = :descrNotificacion")
    , @NamedQuery(name = "NotificacionMensajes.findByEstatus", query = "SELECT n FROM NotificacionMensajes n WHERE n.estatus = :estatus")})
public class NotificacionMensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOTIFICACION")
    private Integer idNotificacion;
    @Size(max = 300)
    @Column(name = "DESCR_NOTIFICACION")
    private String descrNotificacion;
    @Column(name = "ESTATUS")
    private Integer estatus;

    public NotificacionMensajes() {
    }

    public NotificacionMensajes(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getDescrNotificacion() {
        return descrNotificacion;
    }

    public void setDescrNotificacion(String descrNotificacion) {
        this.descrNotificacion = descrNotificacion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionMensajes)) {
            return false;
        }
        NotificacionMensajes other = (NotificacionMensajes) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.relino.relinov2.entities.NotificacionMensajes[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
