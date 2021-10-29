/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Transactions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DuyAnh
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> implements TransactionsFacadeLocal {

    @PersistenceContext(unitName = "InspiringFurniture-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }

    @Override
    public List<Transactions> findByUser(Object userId) {
        List<Transactions> trans =  em.createQuery("SELECT t FROM Transactions t WHERE t.userId = :user_id")
                .setParameter("user_id", userId).getResultList();
        return trans;
    }
    
}
