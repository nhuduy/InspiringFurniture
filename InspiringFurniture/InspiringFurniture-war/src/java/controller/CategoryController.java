/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Category;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import models.CategoryFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private CategoryFacadeLocal categoryFacade;

    private Category category = new Category();

    private Integer cateId;
    private String cateName;
    private String cateIcon;
    private String cateDescription;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateIcon() {
        return cateIcon;
    }

    public void setCateIcon(String cateIcon) {
        this.cateIcon = cateIcon;
    }

    public String getCateDescription() {
        return cateDescription;
    }

    public void setCateDescription(String cateDescription) {
        this.cateDescription = cateDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // List All Category
    public List<Category> listAll() {
        return this.categoryFacade.findAll();
    }

    //Create new Category
    public String add() {

        //        Category cate = new Category();
//
//
//        if (!getFilename(uploadedFile).isEmpty()) {
//            cate.setCateIcon(getFilename(uploadedFile));
////            saveFile();
//            cate.setCateName(cateName);
//            cate.setCateDescription(cateDescription);
//            this.categoryFacade.create(cate);
//            return "category-list";
//        }
////        cate.setCateIcon(uploadedFile.getSubmittedFileName());
//
////        p.setImage(image);
//        else {
//            return "category-add";
//        }
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.categoryFacade.create(category);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.category.getCateName() +'"' + " Created!"));
                
                this.category = new Category();
                return "category-list";
            } else {
                this.categoryFacade.create(category);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.category.getCateName()+ '"' + " Created!"));
                
                this.category = new Category();
                return "category-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "category-add";
    }

    //Delete Category
    public String delete(Category c) {
        this.categoryFacade.remove(c);
        FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + c.getCateName() + '"' + " Deleted!"));
        return "category-list";
    }

    //Edit Category
    public String edit(Category c) {
        this.category = c;
        return "category-edit";
    }

    public String edit() {
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.categoryFacade.edit(this.category);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.category.getCateName()+ '"' + " Updated!"));
                
                this.category = new Category();
                return "category-list";
            } else {
                this.categoryFacade.edit(this.category);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.category.getCateName()+ '"' + " Updated!"));
                
                this.category = new Category();
                return "category-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "category-edit";
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
                .getInitParameter("com.inspiring.UploadCategoryIcon");
        
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

        this.category.setCateIcon(fileName);
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
    public CategoryController() {
    }

}
