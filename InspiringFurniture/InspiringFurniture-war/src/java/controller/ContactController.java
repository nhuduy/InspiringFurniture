/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Contact;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.ContactFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "contactController")
@SessionScoped
public class ContactController implements Serializable {

    @EJB
    private ContactFacadeLocal contactFacade;

    private Contact contact = new Contact();
    
    private Integer contId;
    private String contFullname;
    private String contEmail;
    private String contCompany;
    private String contPhone;
    private String contAddress;
    private String contComment;
    private Date contCreatedAt;
    private String contStats;

    public String getContStats() {
        return contStats;
    }

    public void setContStats(String contStats) {
        this.contStats = contStats;
    }

    public Date getContCreatedAt() {
        return contCreatedAt;
    }

    public void setContCreatedAt(Date contCreatedAt) {
        this.contCreatedAt = contCreatedAt;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
    
    //List all Contact
    public List<Contact> listAll() {
        return this.contactFacade.findAll();
    }

    //Create new Contact
    public String add() {
        this.contactFacade.create(contact);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.contact.getContEmail() + '"' + " Created!"));

        this.contact = new Contact();
        return "contact-list";
    }
    
    // Add new Customer Contact
    public String addCusContact() {
        this.contactFacade.create(contact);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Contact" + '"' + this.contact.getContEmail() + '"' + " Created!"));

        this.contact = new Contact();
        return "index";
    }

    // Delete Contact
    public String delete(Contact c) {
        this.contactFacade.remove(c);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + c.getContEmail() + '"' + " Deleted!"));
        return "contact-list";
    }

    //Edit Contact
    public String edit(Contact c) {
        this.contact = c;
        return "contact-edit";
    }

    public String edit() {
        if (this.contStats.equals("true")){
            this.contact.setContStatus(true);
        }
        if (this.contStats.equals("false")){
            this.contact.setContStatus(false);
        }
        
        this.contactFacade.edit(contact);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.contact.getContEmail() + '"' + " Updated!"));

        this.contact = new Contact();
        return "contact-list";

    }
    
    // Constructor
    public ContactController() {
    }
    
}
