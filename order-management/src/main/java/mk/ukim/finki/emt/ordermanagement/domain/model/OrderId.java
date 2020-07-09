package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderId extends DomainObjectId {

    protected OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    @JsonCreator
    public OrderId(String id) {
        super(id);
    }
}
