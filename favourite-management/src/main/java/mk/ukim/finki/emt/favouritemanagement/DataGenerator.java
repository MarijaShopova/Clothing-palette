package mk.ukim.finki.emt.favouritemanagement;

import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.ProductId;
import mk.ukim.finki.emt.favouritemanagement.domain.model.UserId;
import mk.ukim.finki.emt.favouritemanagement.domain.repository.FavouriteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
class DataGenerator {

    private final FavouriteRepository favouriteRepository;

    DataGenerator(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {

        Set<Favourite> favourites;

        if (favouriteRepository.findAll().size() == 0) {
            favourites = new HashSet<>();
            favourites.add(createFavourite(new FavouriteId("1"), new ProductId("1"), new UserId("1")));
            favourites.add(createFavourite(new FavouriteId("2"), new ProductId("2"), new UserId("1")));
            favourites.add(createFavourite(new FavouriteId("3"), new ProductId("3"), new UserId("1")));
            favouriteRepository.saveAll(favourites);
        }
    }

    private Favourite createFavourite(FavouriteId favouriteId, ProductId productId, UserId userId) {
        return new Favourite(favouriteId, productId, userId);
    }
}