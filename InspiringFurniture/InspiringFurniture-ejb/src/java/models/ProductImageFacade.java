/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.ProductImage;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DuyAnh
 */
@Stateless
public class ProductImageFacade extends AbstractFacade<ProductImage> implements ProductImageFacadeLocal {

    @PersistenceContext(unitName = "InspiringFurniture-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductImageFacade() {
        super(ProductImage.class);
    }

    @Override
    public List<ProductImage> findByProdId(Integer prodId) {
        List<ProductImage> proimg =  em.createQuery("SELECT p FROM ProductImage p WHERE p.prodId = :prod_id")
                .setParameter("prod_id", prodId).getResultList();
        return proimg;
    }

    
    
}
