/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Wishlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DuyAnh
 */
@Stateless
public class WishlistFacade extends AbstractFacade<Wishlist> implements WishlistFacadeLocal {

    @PersistenceContext(unitName = "InspiringFurniture-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WishlistFacade() {
        super(Wishlist.class);
    }

    @Override
    public Wishlist checkExistProduct(Object userId, Object prodId) {
        //1. Tao query intances
        String jpql = "Select w From Wishlist w Where w.userId = :user_id and w.prodId = :prod_id";
        Query query = em.createQuery(jpql);
        query.setParameter("user_id", userId);
        query.setParameter("prod_id", prodId);
        //2. Truy van vao object
        try {
            
            return (Wishlist) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Wishlist> findByUser(Object userId) {
        List<Wishlist> wishlists =  em.createQuery("SELECT w FROM Wishlist w WHERE w.userId = :user_id")
                .setParameter("user_id", userId).getResultList();
        return wishlists;
    }
    
}
