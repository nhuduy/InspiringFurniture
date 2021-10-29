/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.User;
import java.math.BigInteger;
import java.security.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DuyAnh
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "InspiringFurniture-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1,messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length()<32){
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User checkAdmin(String email, String password) {
        //1. Tao query intances
        String jpql = "Select u From User u Where u.userEmail=:user_email and u.userPassword=:user_password";
        Query query = em.createQuery(jpql);
        query.setParameter("user_email", email);
        query.setParameter("user_password",encryptMD5(password));
        //2. Truy van vao object
        try {
            
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User checkCus(String email, String password) {
        //1. Tao query intances
        String jpql = "Select u From User u Where u.userEmail=:user_email and u.userPassword=:user_password";
        Query query = em.createQuery(jpql);
        query.setParameter("user_email", email);
        query.setParameter("user_password",encryptMD5(password));
        //2. Truy van vao object
        try {
            
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User checkForgotCus(String email, String phone) {
        //1. Tao query intances
        String jpql = "Select u From User u Where u.userEmail=:user_email and u.userPhone=:user_phone";
        Query query = em.createQuery(jpql);
        query.setParameter("user_email", email);
        query.setParameter("user_phone",phone);
        //2. Truy van vao object
        try {
            
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User checkEmailIsExist(String userEmail) {
        //1. Tao query intances
        String jpql = "Select u From User u Where u.userEmail=:user_email";
        Query query = em.createQuery(jpql);
        query.setParameter("user_email", userEmail);
        //2. Truy van vao object
        try {
            
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
