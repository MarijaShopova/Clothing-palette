package mk.ukim.finki.emt.favouritemanagement.application.service;


import lombok.NonNull;
import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.ProductId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.UserId;
import mk.ukim.finki.emt.favouritemanagement.domain.repository.FavouriteRepository;
import mk.ukim.finki.emt.favouritemanagement.integration.ProductDeletedEvent;
import mk.ukim.finki.emt.favouritemanagement.integration.UserDeletedEvent;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavouriteCatalog {

    private final FavouriteRepository favouriteRepository;

    public FavouriteCatalog(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @NonNull
    public List<Favourite> findAll() {
        return favouriteRepository.findAll();
    }

    public Optional<Favourite> findById(FavouriteId favouriteId) {
        return favouriteRepository.findById(favouriteId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void deleteFavouritesByProductId(ProductDeletedEvent event) {
        this.favouriteRepository.deleteAllByProductId(event.getProductId());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void deleteFavouritesByUserId(UserDeletedEvent event) {
        this.favouriteRepository.deleteAllByUserId(event.getUserId());
    }

    //user will be taken from user-management module
    public void createFavourite(String id, String productId){
        UserId userId = new UserId("1");
        ProductId productId1 = new ProductId(productId);
        FavouriteId favouriteId = new FavouriteId(id);
        Favourite favourite = new Favourite(favouriteId,productId1,userId);
        this.favouriteRepository.save(favourite);

    }
}
