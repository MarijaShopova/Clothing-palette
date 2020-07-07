package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderId extends DomainObjectId {

    public OrderId(@NonNull String id) {
        super(id);
    }
}
