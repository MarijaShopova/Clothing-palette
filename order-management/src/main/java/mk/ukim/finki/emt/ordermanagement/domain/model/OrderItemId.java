package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {

    private OrderItemId()
    {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(String id) {
        super(id);
    }
}
