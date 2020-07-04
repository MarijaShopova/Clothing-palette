package mk.ukim.finki.emt.favouritemanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "favourites")
public class Favourites extends AbstractEntity<FavouritesId> {

    @Embedded
    private ProductId productId;

    @Embedded
    private UserId userId;

    @Override
    public FavouritesId id() {
        return id;
    }
}
