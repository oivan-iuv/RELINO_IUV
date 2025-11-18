/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.CatColoniasPais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IvanPC
 */
@Stateless
public class CatColoniasPaisFacade extends AbstractFacade<CatColoniasPais> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatColoniasPaisFacade() {
        super(CatColoniasPais.class);
    }
    
    public List<CatColoniasPais> findByClaveMunicipioPais(Integer claveMunicipioPais, Integer claveEstadoPais ) {
        javax.persistence.Query q = getEntityManager().createNamedQuery("CatColoniasPais.findByClaveMunicipioPais");
        q.setParameter("claveMunicipioPais", claveMunicipioPais);
        q.setParameter("claveEstadoPais", claveEstadoPais);
        return q.getResultList();
    }       
}
