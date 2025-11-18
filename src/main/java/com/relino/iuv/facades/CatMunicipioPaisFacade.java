/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.CatMunicipioPais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IvanPC
 */
@Stateless
public class CatMunicipioPaisFacade extends AbstractFacade<CatMunicipioPais> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatMunicipioPaisFacade() {
        super(CatMunicipioPais.class);
    }
    
    public List<CatMunicipioPais> findByEstadoPais(Integer idEstadoPais ) {
        String query = "SELECT c FROM CatMunicipioPais c  WHERE c.idEstadoPais.idEstadoPais=:idEstadoPais ORDER BY c.municipioPais ";
        javax.persistence.Query q = getEntityManager().createQuery(query);
        q.setParameter("idEstadoPais", idEstadoPais);
        
        return q.getResultList();
    }        
    
}
