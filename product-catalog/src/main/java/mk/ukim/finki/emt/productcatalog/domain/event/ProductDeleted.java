package mk.ukim.finki.emt.productcatalog.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.productcatalog.domain.model.FavouriteId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class ProductDeleted implements DomainEvent {

    @JsonProperty("favouriteId")
    private final FavouriteId favouriteId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public ProductDeleted(FavouriteId favouriteId, Instant occurredOn) {
        this.favouriteId = favouriteId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @NonNull
    public FavouriteId favouriteId() {
        return favouriteId;
    }
}
