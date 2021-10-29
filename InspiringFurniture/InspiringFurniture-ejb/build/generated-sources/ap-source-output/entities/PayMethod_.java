package entities;

import entities.OrderDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:53:55")
@StaticMetamodel(PayMethod.class)
public class PayMethod_ { 

    public static volatile SingularAttribute<PayMethod, String> payDescription;
    public static volatile ListAttribute<PayMethod, OrderDetail> orderDetailList;
    public static volatile SingularAttribute<PayMethod, Integer> payId;
    public static volatile SingularAttribute<PayMethod, String> payName;

}