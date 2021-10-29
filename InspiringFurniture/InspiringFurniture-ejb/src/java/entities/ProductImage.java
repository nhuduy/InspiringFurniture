/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DuyAnh
 */
@Entity
@Table(name = "product_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
    , @NamedQuery(name = "ProductImage.findByProImgId", query = "SELECT p FROM ProductImage p WHERE p.proImgId = :proImgId")
    , @NamedQuery(name = "ProductImage.findByProimgFile", query = "SELECT p FROM ProductImage p WHERE p.proimgFile = :proimgFile")
    , @NamedQuery(name = "ProductImage.findByProdId", query = "SELECT p FROM ProductImage p WHERE p.prodId = :prodId")})
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_img_id")
    private Integer proImgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "proimg_file")
    private String proimgFile;
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Product prodId;

    public ProductImage() {
    }

    public ProductImage(Integer proImgId) {
        this.proImgId = proImgId;
    }

    public ProductImage(Integer proImgId, String proimgFile) {
        this.proImgId = proImgId;
        this.proimgFile = proimgFile;
    }

    public Integer getProImgId() {
        return proImgId;
    }

    public void setProImgId(Integer proImgId) {
        this.proImgId = proImgId;
    }

    public String getProimgFile() {
        return proimgFile;
    }

    public void setProimgFile(String proimgFile) {
        this.proimgFile = proimgFile;
    }

    public Product getProdId() {
        return prodId;
    }

    public void setProdId(Product prodId) {
        this.prodId = prodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proImgId != null ? proImgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductImage)) {
            return false;
        }
        ProductImage other = (ProductImage) object;
        if ((this.proImgId == null && other.proImgId != null) || (this.proImgId != null && !this.proImgId.equals(other.proImgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductImage[ proImgId=" + proImgId + " ]";
    }
    
}
