/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Product;
import entities.Promotion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.ProductFacadeLocal;
import models.PromotionFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "promotionController")
@SessionScoped
public class PromotionController implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    @EJB
    private PromotionFacadeLocal promotionFacade;

    private Promotion promotion = new Promotion();

    private Integer promId;
    private String promName;
    private String promContent;
    private Date promFromdate = new Date();
    private Date promEnddate = new Date();

    private Product product;

    public ProductFacadeLocal getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeLocal productFacade) {
        this.productFacade = productFacade;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public String getPromName() {
        return promName;
    }

    public void setPromName(String promName) {
        this.promName = promName;
    }

    public String getPromContent() {
        return promContent;
    }

    public void setPromContent(String promContent) {
        this.promContent = promContent;
    }

    public Date getPromFromdate() {
        return promFromdate;
    }

    public void setPromFromdate(Date promFromdate) {
        this.promFromdate = promFromdate;
    }

    public Date getPromEnddate() {
        return promEnddate;
    }

    public void setPromEnddate(Date promEnddate) {
        this.promEnddate = promEnddate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // List All Product
    public List<Promotion> listAll() {
        return this.promotionFacade.findAll();
    }

    //Create new Contact
    public String add() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fromDate = simpleDateFormat.format(this.promotion.getPromFromdate());
        String endDate = simpleDateFormat.format(this.promotion.getPromEnddate());

        // Parse String to Date obj
        this.promotion.setPromFromdate(simpleDateFormat.parse(fromDate));
        this.promotion.setPromEnddate(simpleDateFormat.parse(endDate));

        this.promotion.setProdId(this.productFacade.find(this.product.getProdId()));
        this.promotionFacade.create(promotion);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.promotion.getPromName() + '"' + " Created!"));

        this.promotion = new Promotion();
        return "promotion-list";
    }

    // Delete Contact
    public String delete(Promotion p) {
        this.promotionFacade.remove(p);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + p.getPromName() + '"' + " Deleted!"));
        return "promotion-list";
    }

    //Edit Contact
    public String edit(Promotion p) {
        this.promotion = p;
        return "promotion-edit";
    }

    public String edit() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fromDate = simpleDateFormat.format(this.promotion.getPromFromdate());
        String endDate = simpleDateFormat.format(this.promotion.getPromEnddate());

        // Parse String to Date obj
        this.promotion.setPromFromdate(simpleDateFormat.parse(fromDate));
        this.promotion.setPromEnddate(simpleDateFormat.parse(endDate));
        
        this.promotion.setProdId(this.productFacade.find(this.product.getProdId()));
        this.promotionFacade.edit(this.promotion);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.promotion.getPromName() + '"' + " Updated!"));

        this.promotion = new Promotion();
        return "promotion-list";

    }

    // Constructor
    public PromotionController() {
        product = new Product();
    }

}
