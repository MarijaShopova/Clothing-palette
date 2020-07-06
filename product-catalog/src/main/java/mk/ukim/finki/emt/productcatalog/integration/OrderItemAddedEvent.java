package mk.ukim.finki.emt.productcatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.model.OrderId;
import mk.ukim.finki.emt.productcatalog.domain.model.OrderItemId;
import mk.ukim.finki.emt.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.model.VariantId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import java.time.Instant;

@Getter
public class OrderItemAddedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("productId")
    private final ProductId productId;

    @JsonProperty("variantId")
    private final VariantId variantId;

    @JsonProperty("quantity")
    private final Quantity quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderItemAddedEvent(OrderId orderId, OrderItemId orderItemId, ProductId productId,
                               VariantId variantId, Quantity quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
