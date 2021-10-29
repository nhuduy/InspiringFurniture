/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DuyAnh
 */
@Entity
@Table(name = "pay_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PayMethod.findAll", query = "SELECT p FROM PayMethod p")
    , @NamedQuery(name = "PayMethod.findByPayId", query = "SELECT p FROM PayMethod p WHERE p.payId = :payId")
    , @NamedQuery(name = "PayMethod.findByPayName", query = "SELECT p FROM PayMethod p WHERE p.payName = :payName")})
public class PayMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pay_id")
    private Integer payId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pay_name")
    private String payName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "pay_description")
    private String payDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payId")
    private List<OrderDetail> orderDetailList;

    public PayMethod() {
    }

    public PayMethod(Integer payId) {
        this.payId = payId;
    }

    public PayMethod(Integer payId, String payName, String payDescription) {
        this.payId = payId;
        this.payName = payName;
        this.payDescription = payDescription;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayMethod)) {
            return false;
        }
        PayMethod other = (PayMethod) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PayMethod[ payId=" + payId + " ]";
    }
       
}
