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
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findByFeedId", query = "SELECT f FROM Feedback f WHERE f.feedId = :feedId")
    , @NamedQuery(name = "Feedback.findByFeedTitle", query = "SELECT f FROM Feedback f WHERE f.feedTitle = :feedTitle")
    , @NamedQuery(name = "Feedback.findByFullname", query = "SELECT f FROM Feedback f WHERE f.fullname = :fullname")
    , @NamedQuery(name = "Feedback.findByEmail", query = "SELECT f FROM Feedback f WHERE f.email = :email")
    , @NamedQuery(name = "Feedback.findByPhone", query = "SELECT f FROM Feedback f WHERE f.phone = :phone")
    , @NamedQuery(name = "Feedback.findByFeedCreatedAt", query = "SELECT f FROM Feedback f WHERE f.feedCreatedAt = :feedCreatedAt")})
public class Feedback implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "feed_status")
    private boolean feedStatus = false;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feed_id")
    private Integer feedId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "feed_title")
    private String feedTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "fullname")
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feed_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feedCreatedAt = new Date();

    public Feedback() {
    }

    public Feedback(Integer feedId) {
        this.feedId = feedId;
    }

    public Feedback(Integer feedId, String feedTitle, String fullname, String email, String phone, String content, Date feedCreatedAt) {
        this.feedId = feedId;
        this.feedTitle = feedTitle;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.content = content;
        this.feedCreatedAt = feedCreatedAt;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFeedCreatedAt() {
        return feedCreatedAt;
    }

    public void setFeedCreatedAt(Date feedCreatedAt) {
        this.feedCreatedAt = feedCreatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedId != null ? feedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedId == null && other.feedId != null) || (this.feedId != null && !this.feedId.equals(other.feedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Feedback[ feedId=" + feedId + " ]";
    }

    public boolean getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(boolean feedStatus) {
        this.feedStatus = feedStatus;
    }
    
}
