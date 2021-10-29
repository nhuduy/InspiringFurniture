/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.PayMethod;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import models.PayMethodFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "payMethodController")
@SessionScoped
public class PayMethodController implements Serializable {

    @EJB
    private PayMethodFacadeLocal payMethodFacade;

    private PayMethod paymethod = new PayMethod();

    private Integer payId;
    private String payName;
    private String payDescription;

    public PayMethod getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(PayMethod paymethod) {
        this.paymethod = paymethod;
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

    //List all Pay method
    public List<PayMethod> listAll() {
        return this.payMethodFacade.findAll();
    }

    //Create new Pay method
    public String add() {
        this.payMethodFacade.create(paymethod);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.paymethod.getPayName() + '"' + " Created!"));

        this.paymethod = new PayMethod();
        return "paymethod-list";
    }

    // Delete Pay method
    public String delete(PayMethod p) {
        this.payMethodFacade.remove(p);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + p.getPayName() + '"' + " Deleted!"));
        return "paymethod-list";
    }

    //Edit Pay method
    public String edit(PayMethod p) {
        this.paymethod = p;
        return "paymethod-edit";
    }

    public String edit() {

        this.payMethodFacade.edit(paymethod);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.paymethod.getPayName() + '"' + " Updated!"));

        this.paymethod = new PayMethod();
        return "paymethod-list";

    }

    // Constructor
    public PayMethodController() {
    }

}
