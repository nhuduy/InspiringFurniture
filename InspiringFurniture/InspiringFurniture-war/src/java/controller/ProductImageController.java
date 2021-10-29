/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Product;
import entities.ProductImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import models.ProductFacadeLocal;
import models.ProductImageFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "productImageController")
@SessionScoped
public class ProductImageController implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    @EJB
    private ProductImageFacadeLocal productImageFacade;

    private ProductImage productImage = new ProductImage();
    
    private Integer proImgId;
    private String proimgFile;
    
    private Product product;
    
    

    public ProductFacadeLocal getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacadeLocal productFacade) {
        this.productFacade = productFacade;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public Integer getProImgId() {
        return proImgId;
    }

    public void setProImgId(Integer proImgId) {
        this.proImgId = proImgId;
    }

    public String getProimgFile() {
        return proimgFile;
    }

    public void setProimgFile(String proimgFile) {
        this.proimgFile = proimgFile;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    // List All ProductImage
    public List<ProductImage> listAll() {
        return this.productImageFacade.findAll();
    }

    //Create new ProductImage
    public String add() {
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.productImage.setProdId(this.productFacade.find(this.product.getProdId()));
                this.productImageFacade.create(productImage);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.productImage.getProimgFile() + '"' + " Created!"));
                
                this.productImage = new ProductImage();
                return "productimages-list";
            } else {
                this.productImage.setProdId(this.productFacade.find(this.product.getProdId()));
                this.productImageFacade.create(productImage);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.productImage.getProimgFile() + '"' + " Created!"));
                
                this.productImage = new ProductImage();
                return "productimages-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(ProductImageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "productimages-add";
    }

    // Delete ProductImage
    public String delete(ProductImage proimg) {
        this.productImageFacade.remove(proimg);
        FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + proimg.getProimgFile() + '"' + " Deleted!"));
        return "productimages-list";
    }

    //Edit ProductImage
    public String edit(ProductImage proimg) {
        this.productImage = proimg;
        return "productimages-edit";
    }

    public String edit() {
        try {
            if (this.uploadedFile != null) {
                this.uploadFile();
                this.productImage.setProdId(this.productFacade.find(this.product.getProdId()));
                this.productImageFacade.edit(productImage);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.productImage.getProimgFile() + '"' + " Updated!"));
                
                this.productImage = new ProductImage();
                return "productimages-list";
            } else {
                this.productImage.setProdId(this.productFacade.find(this.product.getProdId()));
                this.productImageFacade.edit(productImage);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.productImage.getProimgFile() + '"' + " Updated!"));
                
                this.productImage = new ProductImage();
                return "productimages-list";
            }

        } catch (IOException ex) {
            Logger.getLogger(ProductImageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "productimages-edit";
    }

    // get Images by prod_id
    public String getImageByProdId(Object prodId){
        ProductImage proimg =  productImageFacade.find(prodId);
//        List<String> imgList = new ArrayList<>();
//        for (ProductImage proimg: proimgList){
//            imgList.add(proimg.getProimgFile());
//        }
        return proimg.getProimgFile();
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
                .getInitParameter("com.inspiring.UploadProImage");
        
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

        this.productImage.setProimgFile(fileName);
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
    public ProductImageController() {
        this.product = new Product();
    }
    
}
