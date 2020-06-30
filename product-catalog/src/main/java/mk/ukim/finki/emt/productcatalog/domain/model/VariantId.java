package mk.ukim.finki.emt.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class VariantId extends DomainObjectId {

    private VariantId() {
        super(DomainObjectId.randomId(VariantId.class).getId());
    }

    @JsonCreator
    public VariantId(String id) {
        super(id);
    }
}
