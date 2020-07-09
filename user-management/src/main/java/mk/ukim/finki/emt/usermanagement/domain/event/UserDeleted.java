package mk.ukim.finki.emt.usermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;

import java.time.Instant;

public class UserDeleted implements DomainEvent {

    @JsonProperty("userId")
    private final UserId userId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public UserDeleted(UserId userId, Instant occurredOn) {
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }

    @NonNull
    public UserId userId() {
        return userId;
    }
}
