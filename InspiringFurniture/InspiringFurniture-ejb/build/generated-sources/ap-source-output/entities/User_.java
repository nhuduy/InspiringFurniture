package entities;

import entities.OrderDetail;
import entities.Transactions;
import entities.Wishlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SingularAttribute<User, Boolean> userStatus;
    public static volatile SingularAttribute<User, String> userPhone;
    public static volatile SingularAttribute<User, Boolean> userAdmin;
    public static volatile SingularAttribute<User, Date> userCreatedAt;
    public static volatile SingularAttribute<User, String> userIdentity;
    public static volatile ListAttribute<User, Transactions> transactionsList;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> userAddress;
    public static volatile ListAttribute<User, OrderDetail> orderDetailList;
    public static volatile SingularAttribute<User, String> userFullname;
    public static volatile SingularAttribute<User, String> userEmail;
    public static volatile SingularAttribute<User, String> userGender;
    public static volatile ListAttribute<User, Wishlist> wishlistList;
    public static volatile SingularAttribute<User, Date> userBirthday;

}