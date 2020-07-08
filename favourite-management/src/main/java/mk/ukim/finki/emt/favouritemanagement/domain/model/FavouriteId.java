package mk.ukim.finki.emt.favouritemanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;


public class FavouriteId extends DomainObjectId {

    private FavouriteId()
    {
        super(DomainObjectId.randomId(FavouriteId.class).toString());
    }

    @JsonCreator
    public FavouriteId(String id) {
        super(id);
    }
}
