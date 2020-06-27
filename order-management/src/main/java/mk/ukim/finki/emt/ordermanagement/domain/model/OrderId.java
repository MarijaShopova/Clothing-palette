package mk.ukim.finki.emt.ordermanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {

    public OrderId(String id) {
        super(id);
    }


}
