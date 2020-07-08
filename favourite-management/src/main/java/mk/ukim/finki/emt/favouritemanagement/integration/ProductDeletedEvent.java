package mk.ukim.finki.emt.favouritemanagement.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.favouritemanagement.domain.model.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class ProductDeletedEvent implements DomainEvent {

    @JsonProperty("productId")
    private final ProductId productId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public ProductDeletedEvent(ProductId productId, Instant occurredOn) {
        this.productId = productId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
