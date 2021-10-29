package entities;

import entities.Category;
import entities.OrderDetail;
import entities.ProductImage;
import entities.Promotion;
import entities.Wishlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> prodQuantity;
    public static volatile SingularAttribute<Product, Integer> prodId;
    public static volatile ListAttribute<Product, OrderDetail> orderDetailList;
    public static volatile SingularAttribute<Product, String> prodDetaildes;
    public static volatile ListAttribute<Product, ProductImage> productImageList;
    public static volatile SingularAttribute<Product, Category> cateId;
    public static volatile SingularAttribute<Product, String> prodName;
    public static volatile SingularAttribute<Product, Double> prodPrice;
    public static volatile SingularAttribute<Product, Double> prodOldprice;
    public static volatile SingularAttribute<Product, Date> prodUpdatedAt;
    public static volatile ListAttribute<Product, Promotion> promotionList;
    public static volatile SingularAttribute<Product, String> prodShortdes;
    public static volatile ListAttribute<Product, Wishlist> wishlistList;

}