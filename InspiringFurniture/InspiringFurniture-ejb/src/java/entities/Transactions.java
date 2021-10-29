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
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")
    , @NamedQuery(name = "Transactions.findByTranId", query = "SELECT t FROM Transactions t WHERE t.tranId = :tranId")
    , @NamedQuery(name = "Transactions.findByTranCreatedDate", query = "SELECT t FROM Transactions t WHERE t.tranCreatedDate = :tranCreatedDate")
    , @NamedQuery(name = "Transactions.findByTranDeliveryDate", query = "SELECT t FROM Transactions t WHERE t.tranDeliveryDate = :tranDeliveryDate")
    , @NamedQuery(name = "Transactions.findByTranStatus", query = "SELECT t FROM Transactions t WHERE t.tranStatus = :tranStatus")
    , @NamedQuery(name = "Transactions.findByUserId", query = "SELECT t FROM Transactions t WHERE t.userId = :userId")})
public class Transactions implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "tran_total")
    private Double tranTotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tran_id")
    private Integer tranId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tran_created_date")
    @Temporal(TemporalType.DATE)
    private Date tranCreatedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tran_delivery_date")
    @Temporal(TemporalType.DATE)
    private Date tranDeliveryDate;
    @Size(max = 250)
    @Column(name = "tran_delivery_address")
    private String tranDeliveryAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tran_status")
    private String tranStatus;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Transactions() {
    }

    public Transactions(Integer tranId) {
        this.tranId = tranId;
    }

    public Transactions(Integer tranId, Date tranCreatedDate, Date tranDeliveryDate, String tranStatus, User userId) {
        this.tranId = tranId;
        this.tranCreatedDate = tranCreatedDate;
        this.tranDeliveryDate = tranDeliveryDate;
        this.tranStatus = tranStatus;
        this.userId = userId;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public Date getTranCreatedDate() {
        return tranCreatedDate;
    }

    public void setTranCreatedDate(Date tranCreatedDate) {
        this.tranCreatedDate = tranCreatedDate;
    }

    public Date getTranDeliveryDate() {
        return tranDeliveryDate;
    }

    public void setTranDeliveryDate(Date tranDeliveryDate) {
        this.tranDeliveryDate = tranDeliveryDate;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranId != null ? tranId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.tranId == null && other.tranId != null) || (this.tranId != null && !this.tranId.equals(other.tranId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transactions[ tranId=" + tranId + " ]";
    }

    public String getTranDeliveryAddress() {
        return tranDeliveryAddress;
    }

    public void setTranDeliveryAddress(String tranDeliveryAddress) {
        this.tranDeliveryAddress = tranDeliveryAddress;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Double getTranTotal() {
        return tranTotal;
    }

    public void setTranTotal(Double tranTotal) {
        this.tranTotal = tranTotal;
    }
    
}
