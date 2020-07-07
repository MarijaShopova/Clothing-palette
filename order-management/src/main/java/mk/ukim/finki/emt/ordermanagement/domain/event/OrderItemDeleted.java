package mk.ukim.finki.emt.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.model.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import java.time.Instant;

public class OrderItemDeleted  implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final ProductId productId;

    @JsonProperty("quantity")
    private final Quantity quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemDeleted(OrderId orderId, OrderItemId orderItemId, ProductId productId, Quantity quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @NonNull
    public OrderItemId orderItemId() {
        return orderItemId;
    }

    @NonNull
    public ProductId productId() {
        return productId;
    }

    @NonNull
    public Quantity quantity() {
        return quantity;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
