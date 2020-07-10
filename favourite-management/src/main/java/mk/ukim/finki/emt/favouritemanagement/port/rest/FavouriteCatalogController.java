package mk.ukim.finki.emt.favouritemanagement.port.rest;

import mk.ukim.finki.emt.favouritemanagement.application.service.FavouriteCatalog;
import mk.ukim.finki.emt.favouritemanagement.domain.model.Favourite;
import mk.ukim.finki.emt.favouritemanagement.domain.model.FavouriteId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteCatalogController {

    private final FavouriteCatalog favouriteCatalog;

    public FavouriteCatalogController(FavouriteCatalog favouriteCatalog) {
        this.favouriteCatalog = favouriteCatalog;
    }

    @GetMapping
    public List<Favourite> getFavourites() {
        return favouriteCatalog.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favourite> getFavourite(@PathVariable String id) {
        return favouriteCatalog.findById(new FavouriteId(id)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public void createFavourite(@PathVariable String id, @RequestBody String productId) {
        favouriteCatalog.createFavourite(id, productId);
    }
}
