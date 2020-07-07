package mk.ukim.finki.emt.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class OrderCreated implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderCreated(@NonNull OrderId orderId, @NonNull Instant occurredOn) {
        this.orderId = orderId;
        this.occurredOn = occurredOn;
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}

