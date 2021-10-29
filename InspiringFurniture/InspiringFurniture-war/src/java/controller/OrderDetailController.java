/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.OrderDetail;
import entities.PayMethod;
import entities.Product;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.OrderDetailFacadeLocal;
import models.PayMethodFacadeLocal;
import models.ProductFacadeLocal;
import models.UserFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@ManagedBean
@Named(value = "orderDetailController")
@RequestScoped
public class OrderDetailController {

    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    
    @EJB
    private UserFacadeLocal userFacade;

    @EJB
    private PayMethodFacadeLocal payMethodFacade;

    @EJB
    private ProductFacadeLocal productFacade;
    
    private OrderDetail orderDetail = new OrderDetail();
    
    private Product product;
    private User user;
    private PayMethod payMethod;
    
    // Constructor
    public OrderDetailController() {
        payMethod = new PayMethod();
        product = new Product();
        user = new User();
    }
    
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }
    
    

    public UserFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }

    public PayMethodFacadeLocal getPayMethodFacade() {
        return payMethodFacade;
    }

    public void setPayMethodFacade(PayMethodFacadeLocal payMethodFacade) {
        this.payMethodFacade = payMethodFacade;
    }

    public ProductFacadeLocal getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeLocal productFacade) {
        this.productFacade = productFacade;
    }
    
   
    // Get Cart to Order
    public String addItem(){
        Map<Integer, Object> cart =  (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                            .getExternalContext().getSessionMap().get("cart");
        
        if (cart!= null){
                                    
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (Object o : cart.values()) {
                Map<String, Object> d = (Map<String, Object>) o;
                Product product = productFacade.find(Integer.parseInt(d.get("prodId").toString()));
                
                
                OrderDetail orderDetailChecked = orderDetailFacade.checkExistProduct((User) FacesContext.getCurrentInstance()
                                            .getExternalContext().getSessionMap()
                                            .get("cus_loged"), product, "waiting");
                if (orderDetailChecked == null){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProdId(product);
                    orderDetail.setUserId((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
                    orderDetail.setPayId(this.payMethod);
                    orderDetail.setPrice(product.getProdPrice());
                    orderDetail.setQuantity(Integer.parseInt(d.get("quantity").toString()));
                    orderDetail.setOrderStatus("waiting");

                    orderDetails.add(orderDetail);
                } else {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProdId(product);
                    orderDetail.setUserId((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
                    orderDetail.setPayId(this.payMethod);
                    orderDetail.setPrice(product.getProdPrice());
                    orderDetail.setQuantity(orderDetailChecked.getQuantity()+ Integer.parseInt(d.get("quantity").toString()));
                    orderDetail.setOrderStatus("waiting");
                    
                    orderDetailFacade.remove(orderDetailChecked);
                    orderDetails.add(orderDetail);
                }
                
                
                
            }
            for (OrderDetail orderDetail: orderDetails){
                orderDetailFacade.create(orderDetail);
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cart");
            return "checkout_info?faces-redirect=true";
        }
        else return "shopping_cart_list?faces-redirect=true";
    }
    // Grand total
    public Double grandTotal(){
        List<OrderDetail> orderList = orderDetailFacade.findByUser((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));

        Double total = 0.0;
        for (OrderDetail item: orderList){
            if (item.getOrderStatus().equals("waiting")){
                total += item.getQuantity() * item.getPrice();
            }
            
        }
        return total;
    }
    // List All OrderDetail
    public List<OrderDetail> listAll() {
        
        return this.orderDetailFacade.findAll();
    }
    
    // List By UserId
    public List<OrderDetail> listByUserId(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            List<OrderDetail> orders = orderDetailFacade.findByUser((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
            List<OrderDetail> orderList = new ArrayList<>();
            for (OrderDetail item: orders){
                if (item.getOrderStatus().equals("waiting")){
                    orderList.add(item);
                }
            }
        return orderList;
        } else return null;
        
    }
    
    
    
    // Delete product item
    public String delete(OrderDetail o) {
        this.orderDetailFacade.remove(o);
        FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + o.getProdId().getProdName() + '"' + " Deleted!"));
        return "checkout_info";
    }
    
}
