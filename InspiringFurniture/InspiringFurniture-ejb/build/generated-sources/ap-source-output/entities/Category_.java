package entities;

import entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, Integer> cateId;
    public static volatile SingularAttribute<Category, String> cateDescription;
    public static volatile SingularAttribute<Category, String> cateIcon;
    public static volatile SingularAttribute<Category, String> cateName;
    public static volatile ListAttribute<Category, Product> productList;

}