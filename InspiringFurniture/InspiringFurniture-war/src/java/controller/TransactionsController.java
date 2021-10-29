/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.OrderDetail;
import entities.Transactions;
import entities.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.OrderDetailFacadeLocal;
import models.TransactionsFacadeLocal;
import models.UserFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@ManagedBean
@Named(value = "transactionsController")
@SessionScoped
public class TransactionsController implements Serializable {

    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;

    @EJB
    private UserFacadeLocal userFacade;

    @EJB
    private TransactionsFacadeLocal transactionsFacade;

    private OrderDetail orderDetail;
    private User user;
    private Transactions transactions = new Transactions();

    public OrderDetailFacadeLocal getOrderDetailFacade() {
        return orderDetailFacade;
    }

    public void setOrderDetailFacade(OrderDetailFacadeLocal orderDetailFacade) {
        this.orderDetailFacade = orderDetailFacade;
    }

    public UserFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    // Get item Order to Checkout
    public String addToTransaction() throws ParseException {
        User userLoged = (User) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("cus_loged");

        if (userLoged != null) {
            Calendar deliveryDate = Calendar.getInstance();
            deliveryDate.setTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Delivery + 7 day
            deliveryDate.add(Calendar.DAY_OF_MONTH, 7);

            // String date
            String deliveryDateStr = simpleDateFormat.format(deliveryDate.getTime());
            String createdDateStr = simpleDateFormat.format(new Date());

            // Parse String to Date obj
            this.transactions.setTranCreatedDate(simpleDateFormat.parse(createdDateStr));
            this.transactions.setTranDeliveryDate(simpleDateFormat.parse(deliveryDateStr));

            List<OrderDetail> orderList = orderDetailFacade.findByUser(userLoged);

            Double total = 0.0;
            for (OrderDetail item : orderList) {
                if (item.getOrderStatus().equals("waiting")) {
                    total += item.getQuantity() * item.getPrice();
                }

            }
            this.transactions.setTranTotal(total);
            this.transactions.setTranStatus("pending");
            this.transactions.setUserId(userLoged);

            this.transactions.setTranDeliveryAddress(userLoged.getUserAddress());

            transactionsFacade.create(this.transactions);

            List<OrderDetail> orders = orderDetailFacade.findByUser(userLoged);
            for (OrderDetail item : orders) {
                if (item.getOrderStatus().equals("waiting")) {
                    item.setOrderStatus("pending");
                    item.setTranId(this.transactions.getTranId());
                    orderDetailFacade.edit(item);
                }

            }
            return "thankyou";
        }
        return "checkout_info";
    }

    // List By UserId
    public List<Transactions> listByUserId(String index) {
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("cus_loged") != null) {
            List<Transactions> trans = transactionsFacade.findByUser((User) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap()
                    .get("cus_loged"));
            List<Transactions> tranList = new ArrayList<>();
//            for (Transactions item: trans){
//                
//                    tranList.add(item);
//                
//            }

            if (index.equals("all_no_last")) {
                for (int i = trans.size() - 2; i >= 0; i--) {
                    tranList.add(trans.get(i));
                }
            }
            if (index.equals("all")) {
                for (int i = trans.size() - 1; i >= 0; i--) {
                    tranList.add(trans.get(i));
                }
            }

            if (index.equals("last_new")) {
                tranList.add(trans.get(trans.size() - 1));
            }

            return tranList;
        } else {
            return null;
        }

    }

    // List By UserId
    public List<Transactions> listByAllUser(User userId, String index) {
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("admin_loged") != null) {
            List<Transactions> trans = transactionsFacade.findByUser(userId);
            List<Transactions> tranList = new ArrayList<>();

            if (index.equals("all")) {
                for (int i = trans.size() - 1; i >= 0; i--) {
                    tranList.add(trans.get(i));
                }
            }

            return tranList;
        } else {
            return null;
        }

    }

    // List Pending/Delivered/All
    public List<Transactions> listAll(String index){
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("admin_loged") != null) {
            List<Transactions> trans = transactionsFacade.findAll();
            List<Transactions> tranList = new ArrayList<>();

            if (index.equals("all")) {
                for (int i = trans.size() - 1; i >= 0; i--) {
                    if (trans.get(i).getTranStatus().equals("pending")){
                        tranList.add(trans.get(i));
                    }
                }
                for (int i = trans.size() - 1; i >= 0; i--) {
                    if (trans.get(i).getTranStatus().equals("delivered")){
                        tranList.add(trans.get(i));
                    }
                }
            }
            if (index.equals("pending")) {
                for (int i = trans.size() - 1; i >= 0; i--) {
                    if (trans.get(i).getTranStatus().equals("pending")){
                        tranList.add(trans.get(i));
                    }
                }
            }
            if (index.equals("delivered")) {
                for (int i = trans.size() - 1; i >= 0; i--) {
                    if (trans.get(i).getTranStatus().equals("delivered")){
                        tranList.add(trans.get(i));
                    }
                }
            }                


            return tranList;
        } else {
            return null;
        }
    }
    
    
    // View Transaction details
    public String view(Transactions tran) {
        this.transactions = tran;
        return "billing_details";
    }

    // List By TranId
    public List<OrderDetail> listByTranId() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("cus_loged") != null) {
            List<OrderDetail> orders = orderDetailFacade.findByTransaction(transactions.getTranId());
            List<OrderDetail> orderList = new ArrayList<>();
            for (OrderDetail item : orders) {
                orderList.add(item);
            }
            return orderList;
        } else {
            return null;
        }

    }
    
    // View Transaction details
    public String viewBilling(Transactions tran) {
        this.transactions = tran;
        return "billing-info?faces-redirect=true";
    }
    
    // List By TranId
    public List<OrderDetail> listBilling() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("admin_loged") != null) {
            List<OrderDetail> orders = orderDetailFacade.findByTransaction(transactions.getTranId());
            List<OrderDetail> orderList = new ArrayList<>();
            for (OrderDetail item : orders) {
                orderList.add(item);
            }
            return orderList;
        } else {
            return null;
        }

    }
    
    public String setDelivered(Transactions tran){
        this.transactions = tran;
        if(this.transactions.getTranStatus().equals("pending")){
            this.transactions.setTranStatus("delivered");
        }
        this.transactionsFacade.edit(this.transactions);
        return "index";
    }

    
    // Constuctor
    public TransactionsController() {
        orderDetail = new OrderDetail();
        user = new User();
        transactions = new Transactions();
    }

}
