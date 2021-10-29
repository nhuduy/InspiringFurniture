package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Contact.class)
public class Contact_ { 

    public static volatile SingularAttribute<Contact, Integer> contId;
    public static volatile SingularAttribute<Contact, String> contEmail;
    public static volatile SingularAttribute<Contact, String> contCompany;
    public static volatile SingularAttribute<Contact, Date> contCreatedAt;
    public static volatile SingularAttribute<Contact, String> contFullname;
    public static volatile SingularAttribute<Contact, String> contComment;
    public static volatile SingularAttribute<Contact, String> contAddress;
    public static volatile SingularAttribute<Contact, Boolean> contStatus;
    public static volatile SingularAttribute<Contact, String> contPhone;

}