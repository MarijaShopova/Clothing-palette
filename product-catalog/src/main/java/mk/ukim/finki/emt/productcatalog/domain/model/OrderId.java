package mk.ukim.finki.emt.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderId extends DomainObjectId {

    @JsonCreator
    public OrderId(String id) {
        super(id);
    }
}

