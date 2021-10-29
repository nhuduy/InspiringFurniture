package entities;

import entities.Product;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Wishlist.class)
public class Wishlist_ { 

    public static volatile SingularAttribute<Wishlist, Product> prodId;
    public static volatile SingularAttribute<Wishlist, Integer> wishId;
    public static volatile SingularAttribute<Wishlist, User> userId;

}