package mk.ukim.finki.emt.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {

    private ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public ProductId(String id) {
        super(id);
    }
}

