/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DuyAnh
 */
@Local
public interface OrderDetailFacadeLocal {

    void create(OrderDetail orderDetail);

    void edit(OrderDetail orderDetail);

    void remove(OrderDetail orderDetail);

    OrderDetail find(Object id);

    List<OrderDetail> findAll();

    List<OrderDetail> findRange(int[] range);

    int count();
    
    List<OrderDetail> findByUser(Object userId);
    
    List<OrderDetail> findByTransaction(Integer tranId);
    
    OrderDetail checkExistProduct(Object userId, Object prodId, String orderStatus);
    
}
