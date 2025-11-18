/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.facades;

import com.relino.iuv.entities.Aspirantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ivan
 */
@Stateless
public class AspirantesFacade extends AbstractFacade<Aspirantes> {

    @PersistenceContext(unitName = "com.relino_RelinoV2Maven_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AspirantesFacade() {
        super(Aspirantes.class);
    }

    public List<Aspirantes> findIfAspExist(Aspirantes asp, int opc) {
        javax.persistence.Query q = null;

        switch (opc) {

            case 1:
                q = getEntityManager().createNamedQuery("Aspirantes.findByRfc");
                q.setParameter("rfc", asp.getRfc().replaceAll("-", "").trim());
                break;
            case 2:
                q = getEntityManager().createNamedQuery("Aspirantes.findByCurp");
                q.setParameter("curp", asp.getCurp().replaceAll("-", "").trim());
                break;

        }

        return q.getResultList();

    }
}
