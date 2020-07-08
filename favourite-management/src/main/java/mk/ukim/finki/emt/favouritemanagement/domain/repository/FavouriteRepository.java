package mk.ukim.finki.emt.favouritemanagement.domain.repository;

import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {

    void deleteAllByProductId(ProductId productId);
}
