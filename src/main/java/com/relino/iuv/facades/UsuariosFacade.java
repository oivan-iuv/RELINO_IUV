/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author IvanPC
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    
     // Metodo para buscar al usuario por su user name
    public Usuarios findByUsuarios(String usuario) {
        Usuarios user = new Usuarios();

        try {
            Query q = getEntityManager().createNamedQuery("Usuarios.findByUsuario");
            q.setParameter("usuario", usuario);
            user = (Usuarios) q.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }    
    public Usuarios findByIdUsuario(Integer idUsuario) {
        javax.persistence.Query query = getEntityManager().createNamedQuery("Usuario.findByIdUsuario");
        query.setParameter("idUsuario", idUsuario);
        try {
            return (Usuarios) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }    
    
    // Query para remover los usuarios del Caché.
    public void remueveCacheUsuario (Usuarios loguedUser) {
        getEntityManager().getEntityManagerFactory().getCache().evict(Usuarios.class, 
                loguedUser.getIdUsuario());
        getEntityManager().clear();
    }    
    
}
