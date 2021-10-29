/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DuyAnh
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProdId", query = "SELECT p FROM Product p WHERE p.prodId = :prodId")
    , @NamedQuery(name = "Product.findByProdName", query = "SELECT p FROM Product p WHERE p.prodName = :prodName")
    , @NamedQuery(name = "Product.findByProdOldprice", query = "SELECT p FROM Product p WHERE p.prodOldprice = :prodOldprice")
    , @NamedQuery(name = "Product.findByProdPrice", query = "SELECT p FROM Product p WHERE p.prodPrice = :prodPrice")
    , @NamedQuery(name = "Product.findByProdQuantity", query = "SELECT p FROM Product p WHERE p.prodQuantity = :prodQuantity")
    , @NamedQuery(name = "Product.findByProdUpdatedAt", query = "SELECT p FROM Product p WHERE p.prodUpdatedAt = :prodUpdatedAt")})
public class Product implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_oldprice")
    private Double prodOldprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_price")
    private Double prodPrice;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_id")
    private Integer prodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "prod_name")
    private String prodName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "prod_shortdes")
    private String prodShortdes;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "prod_detaildes")
    private String prodDetaildes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_quantity")
    private int prodQuantity;
    @Column(name = "prod_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodUpdatedAt = new Date();
    @JoinColumn(name = "cate_id", referencedColumnName = "cate_id")
    @ManyToOne(optional = false)
    private Category cateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private List<ProductImage> productImageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private List<Wishlist> wishlistList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private List<OrderDetail> orderDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private List<Promotion> promotionList;

    public Product() {
    }

    public Product(Integer prodId) {
        this.prodId = prodId;
    }

    public Product(Integer prodId, String prodName, Double prodOldprice, Double prodPrice, String prodShortdes, String prodDetaildes, int prodQuantity) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodOldprice = prodOldprice;
        this.prodPrice = prodPrice;
        this.prodShortdes = prodShortdes;
        this.prodDetaildes = prodDetaildes;
        this.prodQuantity = prodQuantity;
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

    public Double getProdOldprice() {
        return prodOldprice;
    }

    public void setProdOldprice(Double prodOldprice) {
        this.prodOldprice = prodOldprice;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
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

    public void setProdUpdatedAt(Date prodUpdatedAt) {
        this.prodUpdatedAt = prodUpdatedAt;
    }

    public Category getCateId() {
        return cateId;
    }

    public void setCateId(Category cateId) {
        this.cateId = cateId;
    }

    @XmlTransient
    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    @XmlTransient
    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @XmlTransient
    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ prodId=" + prodId + " ]";
    }

    
    
}
