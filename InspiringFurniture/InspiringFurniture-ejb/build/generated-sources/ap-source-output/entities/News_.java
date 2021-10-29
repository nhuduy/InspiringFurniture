package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, String> newsIntro;
    public static volatile SingularAttribute<News, String> newsImage;
    public static volatile SingularAttribute<News, Integer> newsId;
    public static volatile SingularAttribute<News, String> newsContent;
    public static volatile SingularAttribute<News, Date> newsCreatedAt;
    public static volatile SingularAttribute<News, String> newsTitle;
    public static volatile SingularAttribute<News, Boolean> newsStatus;

}