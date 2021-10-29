/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Category;
import entities.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.CategoryFacadeLocal;
import models.ProductFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private CategoryFacadeLocal categoryFacade;

    @EJB
    private ProductFacadeLocal productFacade;
    

    private Product product = new Product();
        
    private Integer prodId;
    private String prodName;
    private BigDecimal prodOldprice;
    private BigDecimal prodPrice;
    private String prodShortdes;
    private String prodDetaildes;
    private int prodQuantity;
    private Date prodUpdatedAt;
    
    private Category category;

    public Category getCategory() {
        return category;
    }

    private Product productDetail = new Product();

    public Product getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(Product productDetail) {
        this.productDetail = productDetail;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryFacadeLocal getCategoryFacade() {
        return categoryFacade;
    }

    public void setCategoryFacade(CategoryFacadeLocal categoryFacade) {
        this.categoryFacade = categoryFacade;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public BigDecimal getProdOldprice() {
        return prodOldprice;
    }

    public void setProdOldprice(BigDecimal prodOldprice) {
        this.prodOldprice = prodOldprice;
    }

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdShortdes() {
        return prodShortdes;
    }

    public void setProdShortdes(String prodShortdes) {
        this.prodShortdes = prodShortdes;
    }

    public String getProdDetaildes() {
        return prodDetaildes;
    }

    public void setProdDetaildes(String prodDetaildes) {
        this.prodDetaildes = prodDetaildes;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Date getProdUpdatedAt() {
        return prodUpdatedAt;
    }

//    private Category category;
//    private Promotion promotion;
    public void setProdUpdatedAt(Date prodUpdatedAt) {
        this.prodUpdatedAt = prodUpdatedAt;
    }

    
    // List All Product
    public List<Product> listAll(){
        return this.productFacade.findAll();
    }
    
    // Minh
    // List by sofa
    public List<Product> listBySofa () {
        Category sofa = new Category();
        sofa.setCateId(2);
        List<Product> listsofa = this.productFacade.findProductByCate(sofa);
        return listsofa;
    }
    
    // List by chair
    public List<Product> listByChair () {
        Category chair = new Category();
        chair.setCateId(3);
        List<Product> listchair = this.productFacade.findProductByCate(chair);
        return listchair;
    }
    
    // List by table
    public List<Product> listByTable () {
        Category table = new Category();
        table.setCateId(4);
        List<Product> listtable = this.productFacade.findProductByCate(table);
        return listtable;
    }
    
    // List by decoration
    public List<Product> listByDecor () {
        Category decor = new Category();
        decor.setCateId(1);
        List<Product> listdecor = this.productFacade.findProductByCate(decor);
        return listdecor;
    }
    // Minh

    // Product details
    public String productDetail(Product p){
        this.productDetail = p;
        
        
        return "product_detail";
    }

    //Create new Product
    public String add() {
        
        this.product.setCateId(this.categoryFacade.find(this.category.getCateId()));
        this.productFacade.create(product);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.product.getProdName() + '"' + " Created!"));

        this.product = new Product();
        return "product-list";
    }

    // Delete Product
    public String delete(Product p) {
        this.productFacade.remove(p);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + p.getProdName() + '"' + " Deleted!"));
        return "product-list";
    }
    
    //Edit Contact
    public String edit(Product p) {
        this.product = p;
        return "product-edit";
    }

    public String edit() {

        this.product.setCateId(this.categoryFacade.find(this.category.getCateId()));
        this.productFacade.edit(this.product);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.product.getProdName() + '"' + " Updated!"));

        this.product = new Product();
        return "product-list";

    }
    
    // Constructor
    public ProductController() {
        category = new Category();
    }
    
}
