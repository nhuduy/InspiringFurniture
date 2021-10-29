/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Product;
import entities.User;
import entities.Wishlist;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import models.ProductFacadeLocal;
import models.WishlistFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@ManagedBean
@Named(value = "wishlistController")
@SessionScoped
public class WishlistController implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    @EJB
    private WishlistFacadeLocal wishlistFacade;

    private Wishlist wishlist = new Wishlist();
    
    private Product product;
    
    private Boolean checkExist = true;

    public Boolean getCheckExist() {
        return checkExist;
    }

    public void setCheckExist(Boolean checkExist) {
        this.checkExist = checkExist;
    }
    
    
    
    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public ProductFacadeLocal getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeLocal productFacade) {
        this.productFacade = productFacade;
    }

    
    public WishlistController() {
    }
    
    @PostConstruct
    public void init(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("wish_list") == null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("wish_list");
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("wish_list", new HashMap<>());
//            } else {
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("wish_list");
//                FacesContext.getCurrentInstance().getExternalContext()
//                        .getSessionMap().put("wish_list", new HashMap<>());
            }
        }
        
    }
        
    // Get Items to Wishlist
    public String addItemWishList(int prodId, String prodName, BigDecimal price){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("wish_list") == null)
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("wish_list", new HashMap<>());
                
             
            Map<Integer, Object> wish_list =  (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                            .getExternalContext().getSessionMap().get("wish_list");
            if (wish_list.get(prodId) == null){
                Map<String, Object> d = new HashMap<>();
                d.put("prodId", prodId);
//                d.put("userId", userId);
                d.put("prodName", prodName);
                d.put("prodPrice", price);
//                d.put("quantity", 1);

                wish_list.put(prodId, d);
            } 
            else {
                FacesContext.getCurrentInstance()
                    .addMessage(null, new  FacesMessage("Product already exist!"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("wish_list");
                return "wishlist";
            }
            this.addWishlist();
            if (this.checkExist == false){
                FacesContext.getCurrentInstance()
                    .addMessage(null, new  FacesMessage("Product already exist!"));
                return "wishlist";
            } else {
//                FacesContext.getCurrentInstance()
//                    .addMessage(null, new  FacesMessage("Product added to Wishlist!"));
                return "wishlist";
            }
            
        } else {
            return "login";
        }
        
//        return "";
    }
    
    // Get Wishlist
    public List<Map<String, Object>> getWishList(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("wish_list") == null){
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("wish_list", new HashMap<>());
            }
            Map<Integer, Object> wish_list = (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                        .getExternalContext()
                                                        .getSessionMap().get("wish_list");
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object o: wish_list.values()){
            Map<String, Object> d = (Map<String, Object>) o;
            result.add(d);
        }
        
        return result;
        } 
        return null;
    }
    
    // Add Wishlist to Database
    public void addWishlist(){
        Map<Integer, Object> wish_list =  (Map<Integer, Object>) FacesContext.getCurrentInstance()
                                                            .getExternalContext().getSessionMap().get("wish_list");
        
        if (wish_list!= null){
                                    
            List<Wishlist> wishlists = new ArrayList<>();
            for (Object o : wish_list.values()) {
                Map<String, Object> d = (Map<String, Object>) o;
                Product product = productFacade.find(Integer.parseInt(d.get("prodId").toString()));
                
                
                Wishlist wishlistChecked = wishlistFacade.checkExistProduct(FacesContext.getCurrentInstance()
                                            .getExternalContext().getSessionMap()
                                            .get("cus_loged"), product);
                if (wishlistChecked == null){
                    Wishlist wishlist = new Wishlist();
                    wishlist.setProdId(product);
                    wishlist.setUserId((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
                    

                    wishlists.add(wishlist);
                } else {
                    FacesContext.getCurrentInstance()
                    .addMessage(null, new  FacesMessage("Product already exist!"));
                    this.checkExist = false;
                }
            }
            for (Wishlist wishlist: wishlists){
                wishlistFacade.create(wishlist);
            }
//            FacesContext.getCurrentInstance()
//                    .addMessage(null, new  FacesMessage("Product added to Wishlist!"));
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("wish_list");
            
            this.checkExist = true;
        }
        else {
            this.checkExist = false;
            FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("wish_list", new HashMap<>());
        }
    }
    
    // List By UserId
    public List<Wishlist> listByUserId(){
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            List<Wishlist> wishlists = wishlistFacade.findByUser((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
            List<Wishlist> wishList = new ArrayList<>();
            for (Wishlist item: wishlists){
                
                    wishList.add(item);
                
            }
        return wishList;
        } else return null;
        
    }
    
    // Count total wishlist
    public int wishCount(){
        int count = 0;
        if (FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap()
                        .get("cus_loged") != null){
            List<Wishlist> wishlists = wishlistFacade.findByUser((User) FacesContext.getCurrentInstance()
                                                .getExternalContext().getSessionMap()
                                                .get("cus_loged"));
            List<Wishlist> wishList = new ArrayList<>();
            for (Wishlist item: wishlists){
                
                    wishList.add(item);
                    count++;
            }
            
        }
        return count;
    }
    
    // Delete product item
    public String delete(Wishlist w) {
        this.wishlistFacade.remove(w);
        FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + w.getProdId().getProdName() + '"' + " Removed!"));
        return "wishlist";
    }
}
