/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import models.UserFacadeLocal;

/**
 *
 * @author DuyAnh
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
    
    @EJB
    private UserFacadeLocal userFacade;
    
    private User user = new User();
    
    private Integer userId;
    private String userEmail;
    private String userPassword;
    private String userFullname;
    private String userGender;
    private String userAddress;
    private String userPhone;
    private Date userBirthday;
    private String userIdentity;
    private boolean userStatus;
    private boolean userAdmin;
    private Date userCreatedAt;
    
    private String confirmPassword;
    private String target = "index";

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String getUserFullname() {
        return userFullname;
    }
    
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }
    
    public String getUserGender() {
        return userGender;
    }
    
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    
    public String getUserAddress() {
        return userAddress;
    }
    
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    public Date getUserBirthday() {
        return userBirthday;
    }
    
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }
    
    public String getUserIdentity() {
        return userIdentity;
    }
    
    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }
    
    public boolean isUserStatus() {
        return userStatus;
    }
    
    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
    
    public boolean isUserAdmin() {
        return userAdmin;
    }
    
    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }
    
    public Date getUserCreatedAt() {
        return userCreatedAt;
    }
    
    public void setUserCreatedAt(Date userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }
    
    public UserController(Integer userId, String userEmail, String userPassword, String userFullname, String userGender, String userAddress, String userPhone, Date userBirthday, String userIdentity, boolean userStatus, boolean userAdmin) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFullname = userFullname;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userBirthday = userBirthday;
        this.userIdentity = userIdentity;
        this.userStatus = userStatus;
        this.userAdmin = userAdmin;
    }

    // List All Product
    public List<User> listAll() {
        return this.userFacade.findAll();
    }

    //Create new Contact
    public String add() throws ParseException {

//        if (!(this.userFacade.find(user).getUserEmail().equals(this.userEmail))) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dob = simpleDateFormat.format(this.user.getUserBirthday());

        // Parse String to Date obj
        this.user.setUserBirthday(simpleDateFormat.parse(dob));
        // encryptMD5 Password
        this.user.setUserPassword(userFacade.encryptMD5(this.user.getUserPassword()));
        this.userFacade.create(user);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.user.getUserEmail() + '"' + " Created!"));
        
        this.user = new User();
        return "user-list";
//        } else {
//            FacesContext.getCurrentInstance()
//                    .addMessage(null, new FacesMessage('"' + this.user.getUserEmail() + '"' + " is Exist!"));
//            return "user-add";
//        }

    }

    // Delete Contact
    public String delete(User u) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String adminMember = fc.getExternalContext().getSessionMap().get("admin_loged").toString();

        //Check session login and don't delete user current login
        if (u.getUserEmail().equals(adminMember)) {
            fc.addMessage(null, new FacesMessage("Can't Delete user current login"));
            return "user-list";
        } else {
            this.userFacade.remove(u);
            fc.getCurrentInstance()
                    .addMessage(null, new FacesMessage('"' + u.getUserEmail() + '"' + " Deleted!"));
            return "user-list";
        }
    }

    //Edit Contact
    public String edit(User u) {
        this.user = u;
        return "user-edit";
    }
    
    public String edit() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dob = simpleDateFormat.format(this.user.getUserBirthday());

        // Parse String to Date obj
        this.user.setUserBirthday(simpleDateFormat.parse(dob));
        // encryptMD5 Password
        this.user.setUserPassword(userFacade.encryptMD5(this.user.getUserPassword()));
        
        this.userFacade.edit(this.user);
        
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage('"' + this.user.getUserEmail() + '"' + " Updated!"));
        
        this.user = new User();
        return "user-list";
        
    }

    //Tao method login kieu String
    public String loginAdmin() {
        User adminLoged = userFacade.checkAdmin(this.userEmail, this.userPassword);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (adminLoged != null) {
            
            if (adminLoged.getUserAdmin() == false) {
                facesContext.addMessage(null, new FacesMessage("User not Administrator!"));
                return "login?faces-redirect=true";
            } else if (adminLoged.getUserStatus() == false) {
                facesContext.addMessage(null, new FacesMessage("Admin not yet Active!"));
                return "login?faces-redirect=true";
            } else {
                facesContext.getExternalContext().getSessionMap().put("admin_loged", adminLoged.getUserEmail());
                facesContext.addMessage(null, new FacesMessage("Admin Login Successfuly"));
                
                return "index"; 
            }
            
        } else {
            facesContext.addMessage(null, new FacesMessage("Admin Login Error"));
            
            return "login?faces-redirect=true";
        }
    }

    //Tao method logout kieu String
    public String logoutAdmin() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        return "login?faces-redirect=true";
    }

    // Check Login Admin
    public String checkLoginAdmin() {
        if (FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("admin_loged") != null) {
            return "index";
        }
        return null;
    }

    //Tao method login kieu String
    public String loginCus() {
        User cusLoged = userFacade.checkCus(this.userEmail, this.userPassword);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        if (cusLoged != null) {
            
            if (cusLoged.getUserStatus() == false) {
                facesContext.addMessage(null, new FacesMessage("Your Email not yet Active!"));
                return "login";
            } else {
                if (cusLoged.getUserAdmin() == true) {
                    facesContext.addMessage(null, new FacesMessage("Admin can't login!"));
                    return "login";
                } else {
                    facesContext.getExternalContext().getSessionMap().put("cus_loged", cusLoged);
                    facesContext.addMessage(null, new FacesMessage("Login Successfuly"));

                    return this.target; 
                }
            }
        } else {
            facesContext.addMessage(null, new FacesMessage("Login Error"));
            return "login";
        }
    }

    //Tao method logout kieu String
    public String logoutCus() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        return "index?faces-redirect=true";
    }

    // Find by User ID
    public User findUserById(Object id) {
        return userFacade.find(id);
    }

    // Check Login Customer
    public String checkLoginCus(String page) {
        if (FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("cus_loged") != null) {
            return page;
        }
        return "login";
    }

    // Reset Password
    public String resetCusPassword(User u){
        this.user = u;
        return "reset_pass";
    }
    
    public String resetCusPassword() {
        User cusForgot = userFacade.checkForgotCus(this.userEmail, this.userPhone);
        if (cusForgot != null) {
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (this.userPassword.equals(this.confirmPassword)){
                
                cusForgot.setUserPassword(userFacade.encryptMD5(this.userPassword));
                this.userFacade.edit(cusForgot);
                
                facesContext.addMessage(null, new FacesMessage("Your " + '"' + "password" + '"' + " had reset!"));
                
                cusForgot = new User();
                return "index";
                
            } else {
                facesContext.addMessage(null, new FacesMessage('"' + "Input Confirm password not match!" + '"'));
                
                return "reset_pass";
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new  FacesMessage("Wrong your profile!"));
            return "reset_pass";
        }
        
    }

    // Update by Nhu Duy
    //    Register User
    public String registerCus() throws ParseException
    {
        if (this.user.getUserPassword().equals(this.confirmPassword)){
            
            if(this.userFacade.checkEmailIsExist(this.userEmail) == null){
                
                this.user.setUserEmail(this.userEmail);
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dob = simpleDateFormat.format(this.user.getUserBirthday());

                // Parse String to Date obj
                this.user.setUserBirthday(simpleDateFormat.parse(dob));
                // encryptMD5 Password
                this.user.setUserPassword(this.userFacade.encryptMD5(this.user.getUserPassword()));
                
                // set Active and set user Customer
                this.user.setUserStatus(true);
                this.user.setUserAdmin(false);

                this.userFacade.create(this.user);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.user.getUserEmail() + '"' + " Created!"));
                this.user = new User();
                return "login";
            } else {
                FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage('"' + "Email already exists!" + '"'));
                return "register";
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage('"' + "Input Confirm password not match!" + '"'));
            return "register";
        }
        
    }
    
    // Update Profile
    public String updateProfile(User u){
        this.user = u;
        return "profile";
    }
    public String updateProfile() throws ParseException{
                
//                this.user.setUserEmail(this.userEmail);
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dob = simpleDateFormat.format(this.user.getUserBirthday());

                // Parse String to Date obj
                this.user.setUserBirthday(simpleDateFormat.parse(dob));
                // encryptMD5 Password
//                this.user.setUserPassword(this.userFacade.encryptMD5(this.user.getUserPassword()));
                
                // set Active and set user Customer
                this.user.setUserStatus(true);
                this.user.setUserAdmin(false);

                this.userFacade.edit(this.user);
                
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage('"' + this.user.getUserEmail() + '"' + " Updated!"));
                this.user = new User();
                return "index";
//            } else {
//                FacesContext.getCurrentInstance()
//                    .addMessage(null, new FacesMessage('"' + "Email already exists!" + '"'));
//                return "register";
//            }
//        } else {
//            FacesContext.getCurrentInstance()
//                    .addMessage(null, new FacesMessage('"' + "Input Confirm password not match!" + '"'));
//            return "register";
//        }
}
    
    
    // Constructor    
    public UserController() {
    }
    
}
