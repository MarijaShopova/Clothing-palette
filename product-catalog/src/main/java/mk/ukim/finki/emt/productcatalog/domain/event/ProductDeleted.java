package mk.ukim.finki.emt.productcatalog.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class ProductDeleted implements DomainEvent {

    @JsonProperty("productId")
    private final ProductId productId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public ProductDeleted(ProductId productId, Instant occurredOn) {
        this.productId = productId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @NonNull
    public ProductId productId() {
        return productId;
    }
}
