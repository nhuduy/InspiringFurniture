package entities;

import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, Integer> tranId;
    public static volatile SingularAttribute<Transactions, Date> tranDeliveryDate;
    public static volatile SingularAttribute<Transactions, Date> tranCreatedDate;
    public static volatile SingularAttribute<Transactions, Double> tranTotal;
    public static volatile SingularAttribute<Transactions, String> tranDeliveryAddress;
    public static volatile SingularAttribute<Transactions, User> userId;
    public static volatile SingularAttribute<Transactions, String> tranStatus;

}