/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.DomicilioAspirante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IvanPC
 */
@Stateless
public class DomicilioAspiranteFacade extends AbstractFacade<DomicilioAspirante> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomicilioAspiranteFacade() {
        super(DomicilioAspirante.class);
    }
    
}
