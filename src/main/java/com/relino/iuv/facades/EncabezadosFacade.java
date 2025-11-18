/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.Encabezados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IvanPC
 */
@Stateless
public class EncabezadosFacade extends AbstractFacade<Encabezados> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncabezadosFacade() {
        super(Encabezados.class);
    }

    public Encabezados findCurrentEncabezado(Integer year) {
        javax.persistence.Query q = null;

        q = getEntityManager().createNamedQuery("Encabezados.findByYear");
        q.setParameter("year", year);

        try {
            return (Encabezados) q.getSingleResult();
        } catch (Exception e) {
            return findCurrentEncabezado((year-1));
        }
        

    }
    
//    public Encabezados findCurrentEncabezado(Integer year) {
//
//        String query = " SELECT e FROM Encabezados e "
//                     + " WHERE e.year = :year";
//        
//        javax.persistence.Query q = getEntityManager().createQuery(query);
//        q.setParameter("year", year);
//        
//
//        return (Encabezados) q.getSingleResult();
//    }    
    

}
