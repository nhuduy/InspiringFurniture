package entities;

import entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(ProductImage.class)
public class ProductImage_ { 

    public static volatile SingularAttribute<ProductImage, Integer> proImgId;
    public static volatile SingularAttribute<ProductImage, String> proimgFile;
    public static volatile SingularAttribute<ProductImage, Product> prodId;

}