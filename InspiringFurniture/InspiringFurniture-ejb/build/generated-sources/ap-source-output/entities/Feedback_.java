package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Integer> feedId;
    public static volatile SingularAttribute<Feedback, String> phone;
    public static volatile SingularAttribute<Feedback, Boolean> feedStatus;
    public static volatile SingularAttribute<Feedback, String> fullname;
    public static volatile SingularAttribute<Feedback, String> feedTitle;
    public static volatile SingularAttribute<Feedback, String> email;
    public static volatile SingularAttribute<Feedback, String> content;
    public static volatile SingularAttribute<Feedback, Date> feedCreatedAt;

}