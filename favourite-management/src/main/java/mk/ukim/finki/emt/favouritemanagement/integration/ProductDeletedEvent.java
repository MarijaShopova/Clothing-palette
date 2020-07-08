package mk.ukim.finki.emt.favouritemanagement.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class ProductDeletedEvent implements DomainEvent {

    @JsonProperty("favouriteId")
    private final FavouriteId favouriteId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public ProductDeletedEvent(FavouriteId favouriteId, Instant occurredOn) {
        this.favouriteId = favouriteId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
