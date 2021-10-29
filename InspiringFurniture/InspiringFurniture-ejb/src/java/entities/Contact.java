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
import javax.persistence.Lob;
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
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findByContId", query = "SELECT c FROM Contact c WHERE c.contId = :contId")
    , @NamedQuery(name = "Contact.findByContFullname", query = "SELECT c FROM Contact c WHERE c.contFullname = :contFullname")
    , @NamedQuery(name = "Contact.findByContEmail", query = "SELECT c FROM Contact c WHERE c.contEmail = :contEmail")
    , @NamedQuery(name = "Contact.findByContCompany", query = "SELECT c FROM Contact c WHERE c.contCompany = :contCompany")
    , @NamedQuery(name = "Contact.findByContPhone", query = "SELECT c FROM Contact c WHERE c.contPhone = :contPhone")
    , @NamedQuery(name = "Contact.findByContAddress", query = "SELECT c FROM Contact c WHERE c.contAddress = :contAddress")})
public class Contact implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cont_status")
    private boolean contStatus = false;

    @Column(name = "cont_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contCreatedAt = new Date();

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cont_id")
    private Integer contId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cont_fullname")
    private String contFullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cont_email")
    private String contEmail;
    @Size(max = 100)
    @Column(name = "cont_company")
    private String contCompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cont_phone")
    private String contPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "cont_address")
    private String contAddress;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "cont_comment")
    private String contComment;

    public Contact() {
    }

    public Contact(Integer contId) {
        this.contId = contId;
    }

    public Contact(Integer contId, String contFullname, String contEmail, String contPhone, String contAddress, String contComment, Date contCreatedAt) {
        this.contId = contId;
        this.contFullname = contFullname;
        this.contEmail = contEmail;
        this.contPhone = contPhone;
        this.contAddress = contAddress;
        this.contComment = contComment;
        this.contCreatedAt = contCreatedAt;
    }

    public Integer getContId() {
        return contId;
    }

    public void setContId(Integer contId) {
        this.contId = contId;
    }

    public String getContFullname() {
        return contFullname;
    }

    public void setContFullname(String contFullname) {
        this.contFullname = contFullname;
    }

    public String getContEmail() {
        return contEmail;
    }

    public void setContEmail(String contEmail) {
        this.contEmail = contEmail;
    }

    public String getContCompany() {
        return contCompany;
    }

    public void setContCompany(String contCompany) {
        this.contCompany = contCompany;
    }

    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public String getContAddress() {
        return contAddress;
    }

    public void setContAddress(String contAddress) {
        this.contAddress = contAddress;
    }

    public String getContComment() {
        return contComment;
    }

    public void setContComment(String contComment) {
        this.contComment = contComment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contId != null ? contId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contId == null && other.contId != null) || (this.contId != null && !this.contId.equals(other.contId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contact[ contId=" + contId + " ]";
    }

    public Date getContCreatedAt() {
        return contCreatedAt;
    }

    public void setContCreatedAt(Date contCreatedAt) {
        this.contCreatedAt = contCreatedAt;
    }

    public boolean getContStatus() {
        return contStatus;
    }

    public void setContStatus(boolean contStatus) {
        this.contStatus = contStatus;
    }
    
}
