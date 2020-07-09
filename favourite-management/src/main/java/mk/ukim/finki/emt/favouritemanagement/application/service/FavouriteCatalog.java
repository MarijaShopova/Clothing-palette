package mk.ukim.finki.emt.favouritemanagement.application.service;


import lombok.NonNull;
import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.favouritemanagement.domain.repository.FavouriteRepository;
import mk.ukim.finki.emt.favouritemanagement.integration.ProductDeletedEvent;
import mk.ukim.finki.emt.favouritemanagement.integration.UserDeletedEvent;
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
}
