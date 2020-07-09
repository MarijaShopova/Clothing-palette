package mk.ukim.finki.emt.favouritemanagement.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.favouritemanagement.domain.model.ProductId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.UserId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class UserDeletedEvent implements DomainEvent {

    @JsonProperty("productId")
    private final UserId userId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public UserDeletedEvent(UserId userId, Instant occurredOn) {
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}