package mk.ukim.finki.emt.favouritemanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "favourites")
public class Favourite extends AbstractEntity<FavouriteId> {

    @Embedded
    private ProductId productId;

    @Embedded
    private UserId userId;

    @Override
    public FavouriteId id() {
        return id;
    }

    public Favourite(){

    }

    public Favourite(FavouriteId favouriteId, ProductId productId, UserId userId){
        super(favouriteId);
        this.productId = productId;
        this.userId = userId;

    }

}
