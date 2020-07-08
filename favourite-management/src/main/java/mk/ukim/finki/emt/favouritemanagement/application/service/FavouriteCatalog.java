package mk.ukim.finki.emt.favouritemanagement.application.service;


import lombok.NonNull;
import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.favouritemanagement.domain.repository.FavouriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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


}
