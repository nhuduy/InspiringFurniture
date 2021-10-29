/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.ProductFacadeLocal;

@ManagedBean
@Named(value = "cartController")
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    public ProductFacadeLocal getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeLocal productFacade) {
        this.productFacade = productFacade;
    }
    
    public CartController() {
    }
    
    @PostConstruct
    public void init(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cart") == null){
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("cart", new HashMap<>());
            }
        }
        
    }
        
    // Get Items to Cart
    public String addItemToCart(int prodId, String prodName, BigDecimal price){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            Map<Integer, Object> cart =  (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                            .getExternalContext().getSessionMap().get("cart");
            if (cart.get(prodId) == null){
                Map<String, Object> d = new HashMap<>();
                d.put("prodId", prodId);
                d.put("prodName", prodName);
                d.put("prodPrice", price);
                d.put("quantity", 1);

                cart.put(prodId, d);
            } else {
                Map<String, Object> d = (Map<String, Object>) cart.get(prodId);
                d.put("quantity", Integer.parseInt(d.get("quantity").toString()) + 1);
            }
        } else {
            return "login?faces-redirect=true";
        }
        
        return "";
    }
    
    // Get Cart
    public List<Map<String, Object>> getCart(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cart") == null){
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("cart", new HashMap<>());
            }
            Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                        .getExternalContext()
                                                        .getSessionMap().get("cart");
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object o: cart.values()){
            Map<String, Object> d = (Map<String, Object>) o;
            result.add(d);
        }
        
        return result;
        } 
        return null;
    }
}
