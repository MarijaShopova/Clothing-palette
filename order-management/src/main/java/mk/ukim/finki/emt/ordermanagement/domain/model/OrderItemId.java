package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;


@Embeddable
@Getter
public class OrderItemId extends DomainObjectId {

    protected OrderItemId()
    {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public OrderItemId(String id) {
        super(id);
    }
}
