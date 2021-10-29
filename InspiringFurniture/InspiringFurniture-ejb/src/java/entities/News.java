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
@Table(name = "news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")
    , @NamedQuery(name = "News.findByNewsId", query = "SELECT n FROM News n WHERE n.newsId = :newsId")
    , @NamedQuery(name = "News.findByNewsTitle", query = "SELECT n FROM News n WHERE n.newsTitle = :newsTitle")
    , @NamedQuery(name = "News.findByNewsImage", query = "SELECT n FROM News n WHERE n.newsImage = :newsImage")
    , @NamedQuery(name = "News.findByNewsStatus", query = "SELECT n FROM News n WHERE n.newsStatus = :newsStatus")
    , @NamedQuery(name = "News.findByNewsCreatedAt", query = "SELECT n FROM News n WHERE n.newsCreatedAt = :newsCreatedAt")})
public class News implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "news_id")
    private Integer newsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "news_title")
    private String newsTitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "news_intro")
    private String newsIntro;
    @Basic(optional = false)
    @Lob
    @Column(name = "news_content")
    private String newsContent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "news_image")
    private String newsImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "news_status")
    private boolean newsStatus;
    @Basic(optional = false)
    @Column(name = "news_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date newsCreatedAt = new Date();

    public News() {
    }

    public News(Integer newsId) {
        this.newsId = newsId;
    }

    public News(Integer newsId, String newsTitle, String newsIntro, String newsContent, String newsImage, Boolean newsStatus, Date newsCreatedAt) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsIntro = newsIntro;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
        this.newsStatus = newsStatus;
        this.newsCreatedAt = newsCreatedAt;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsIntro() {
        return newsIntro;
    }

    public void setNewsIntro(String newsIntro) {
        this.newsIntro = newsIntro;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public Boolean getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(Boolean newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Date getNewsCreatedAt() {
        return newsCreatedAt;
    }

    public void setNewsCreatedAt(Date newsCreatedAt) {
        this.newsCreatedAt = newsCreatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsId != null ? newsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.newsId == null && other.newsId != null) || (this.newsId != null && !this.newsId.equals(other.newsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.News[ newsId=" + newsId + " ]";
    }
    
}
