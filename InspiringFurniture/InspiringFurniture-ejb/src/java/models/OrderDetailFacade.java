/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderDetail;
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
public class OrderDetailFacade extends AbstractFacade<OrderDetail> implements OrderDetailFacadeLocal {

    @PersistenceContext(unitName = "InspiringFurniture-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailFacade() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> findByUser(Object userId) {
        List<OrderDetail> orders =  em.createQuery("SELECT o FROM OrderDetail o WHERE o.userId = :user_id")
                .setParameter("user_id", userId).getResultList();
        return orders;
    }

    @Override
    public OrderDetail checkExistProduct(Object userId, Object prodId, String orderStatus) {
        //1. Tao query intances
        String jpql = "Select o From OrderDetail o Where o.userId = :user_id and o.prodId = :prod_id and o.orderStatus = :order_status";
        Query query = em.createQuery(jpql);
        query.setParameter("user_id", userId);
        query.setParameter("prod_id", prodId);
        query.setParameter("order_status", orderStatus);
        //2. Truy van vao object
        try {
            
            return (OrderDetail) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderDetail> findByTransaction(Integer tranId) {
        List<OrderDetail> orders =  em.createQuery("SELECT o FROM OrderDetail o WHERE o.tranId = :tran_id")
                .setParameter("tran_id", tranId).getResultList();
        return orders;
    }
    
}
