/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.News;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import models.NewsFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "newsController")
@SessionScoped
public class NewsController implements Serializable {

    @EJB
    private NewsFacadeLocal newsFacade;

    private News news = new News();

    private Integer newsId;
    private String newsTitle;
    private String newsIntro;
    private String newsContent;
    private String newsImage;
    private Boolean newsStatus;
    private Date newsCreatedAt;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
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

    // List All News
    public List<News> listAll() {
        return this.newsFacade.findAll();
    }

    //Create new News
    public String add() {
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.newsFacade.create(news);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.news.getNewsTitle() + '"' + " Created!"));
                
                this.news = new News();
                return "news-list";
            } else {
                this.newsFacade.create(news);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.news.getNewsTitle() + '"' + " Created!"));
                
                this.news = new News();
                return "news-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "news-add";
    }

    // Delete News
    public String delete(News n) {
        this.newsFacade.remove(n);
        FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + n.getNewsTitle() + '"' + " Deleted!"));
        return "news-list";
    }

    //Edit News
    public String edit(News n) {
        this.news = n;
        return "news-edit";
    }

    public String edit() {
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.newsFacade.edit(news);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.news.getNewsTitle() + '"' + " Updated!"));
                
                this.news = new News();
                return "news-list";
            } else {
                this.newsFacade.edit(news);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.news.getNewsTitle() + '"' + " Updated!"));
                
                this.news = new News();
                return "news-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "news-edit";
    }

    // Upload file
    private Part uploadedFile;
//    private String folder = FacesContext.getCurrentInstance()
//            .getExternalContext()
//            .getRealPath("D:\\");

    public void uploadFile() throws IOException {
        String fileName = this.uploadedFile.getSubmittedFileName();

        // Path get web.xml 
        String str = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("com.inspiring.UploadNewImage");
        
        // Path of Deploy
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath(str) + "/" + fileName;
                
        // Path of Project
//        String path = str + "/" + fileName;

        try (InputStream in = this.uploadedFile.getInputStream();
                FileOutputStream out = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            int byteRead;
            while ((byteRead = in.read(b)) != -1) {
                out.write(b, 0, byteRead);
            }
        }

        this.news.setNewsImage(fileName);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage('"' + fileName + '"' + " Uploaded!"));
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    // End Upload file

    // Constructor
    public NewsController() {
    }

}
