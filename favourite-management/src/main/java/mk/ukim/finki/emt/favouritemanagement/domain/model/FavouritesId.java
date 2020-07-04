package mk.ukim.finki.emt.favouritemanagement.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class FavouritesId extends DomainObjectId {

    public FavouritesId(@NonNull String id) {
        super(id);
    }
}
