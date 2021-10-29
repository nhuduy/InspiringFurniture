package entities;

import entities.Product;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, Integer> promId;
    public static volatile SingularAttribute<Promotion, String> promName;
    public static volatile SingularAttribute<Promotion, Date> promFromdate;
    public static volatile SingularAttribute<Promotion, String> promContent;
    public static volatile SingularAttribute<Promotion, Date> promEnddate;
    public static volatile SingularAttribute<Promotion, Product> prodId;

}