package mk.ukim.finki.emt.favouritemanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "favourites")
public class Favourite extends AbstractEntity<FavouriteId> {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))})
    private ProductId productId;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))})
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
