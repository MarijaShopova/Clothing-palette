package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {

    protected OrderId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public OrderId(String id) {
        super(id);
    }
}
