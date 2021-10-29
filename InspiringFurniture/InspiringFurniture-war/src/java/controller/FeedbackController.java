/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Feedback;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.FeedbackFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "feedbackController")
@SessionScoped
public class FeedbackController implements Serializable {

    @EJB
    private FeedbackFacadeLocal feedbackFacade;

    private Feedback feedback = new Feedback();
    
    private Integer feedId;
    private String feedTitle;
    private String fullname;
    private String email;
    private String phone;
    private String content;
    private Date feedCreatedAt;
    private String feedStatus;

    public String getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(String feedStatus) {
        this.feedStatus = feedStatus;
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

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
     
    //List all Feedback
    public List<Feedback> listAll() {
        return this.feedbackFacade.findAll();
    }

    //Create new Feedback
    public String add() {
        this.feedbackFacade.create(feedback);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.feedback.getFeedTitle()+ '"' + " Created!"));

        this.feedback = new Feedback();
        return "feedback-list";
    }
    
    // Add new Customer Feedback 
    public String addCusFeedback() {
        this.feedbackFacade.create(feedback);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Feedback" + '"' + this.feedback.getFeedTitle()+ '"' + " Added!"));

        this.feedback = new Feedback();
        return "index";
    }

    // Delete Feedback
    public String delete(Feedback fb) {
        this.feedbackFacade.remove(fb);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + fb.getFeedTitle()+ '"' + " Deleted!"));
        return "feedback-list";
    }

    //Edit Feedback
    public String edit(Feedback fb) {
        this.feedback = fb;
        return "feedback-edit";
    }

    public String edit() {
        if (this.feedStatus.equals("true")){
            this.feedback.setFeedStatus(true);
        }
        if (this.feedStatus.equals("false")){
            this.feedback.setFeedStatus(false);
        }
        this.feedbackFacade.edit(feedback);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.feedback.getFeedTitle()+ '"' + " Updated!"));

        this.feedback = new Feedback();
        return "feedback-list";

    }
    
    // Constructor
    public FeedbackController() {
    }
    
}
