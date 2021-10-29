/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DuyAnh
 */
@Entity
@Table(name = "promotion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p")
    , @NamedQuery(name = "Promotion.findByPromId", query = "SELECT p FROM Promotion p WHERE p.promId = :promId")
    , @NamedQuery(name = "Promotion.findByPromName", query = "SELECT p FROM Promotion p WHERE p.promName = :promName")
    , @NamedQuery(name = "Promotion.findByPromFromdate", query = "SELECT p FROM Promotion p WHERE p.promFromdate = :promFromdate")
    , @NamedQuery(name = "Promotion.findByPromEnddate", query = "SELECT p FROM Promotion p WHERE p.promEnddate = :promEnddate")})
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prom_id")
    private Integer promId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "prom_name")
    private String promName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "prom_content")
    private String promContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prom_fromdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promFromdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prom_enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promEnddate;
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Product prodId;

    public Promotion() {
    }

    public Promotion(Integer promId) {
        this.promId = promId;
    }

    public Promotion(Integer promId, String promName, String promContent, Date promFromdate, Date promEnddate) {
        this.promId = promId;
        this.promName = promName;
        this.promContent = promContent;
        this.promFromdate = promFromdate;
        this.promEnddate = promEnddate;
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

    public Product getProdId() {
        return prodId;
    }

    public void setProdId(Product prodId) {
        this.prodId = prodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promId != null ? promId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.promId == null && other.promId != null) || (this.promId != null && !this.promId.equals(other.promId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Promotion[ promId=" + promId + " ]";
    }
    
}
