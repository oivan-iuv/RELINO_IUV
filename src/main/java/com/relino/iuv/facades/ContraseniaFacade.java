/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.Contrasenia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ERVING GUTIERREZ
 */
    
@Stateless
public class ContraseniaFacade extends AbstractFacade<Contrasenia> {
    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContraseniaFacade() {
        super(Contrasenia.class);
    }
    
    // Query para validar el usuario y contraseña
    public Boolean findByUserAndPassBoolean (Integer idUsuario, String contrasenia) {
        boolean validaPass = false;
        try {
            javax.persistence.Query q = getEntityManager().createNamedQuery("Contrasenia.findByPasswordAndId");
            q.setParameter("idUsuario", idUsuario);
            q.setParameter("password", contrasenia);
            List<Contrasenia> list = q.getResultList();
            validaPass = (list != null && list.size() > 0);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());            
          
        } finally{
            
        }
        return validaPass;
    }
    
    public Contrasenia findByPassActiva (Integer idUsuario) {
        Contrasenia passActiva = null;
        try {
            javax.persistence.Query q = getEntityManager().createNamedQuery("Contrasenia.findByPasswordActiva");
            q.setParameter("idUsuario", idUsuario);
            List<Contrasenia> list = q.getResultList();
            if (!list.isEmpty()) {
                passActiva = list.get(0);
            }
        } catch (javax.ejb.EJBException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            
        }
        return passActiva;
    }
}
