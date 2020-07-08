package mk.ukim.finki.emt.productcatalog.port.rest;

import mk.ukim.finki.emt.productcatalog.application.service.ProductCatalog;
import mk.ukim.finki.emt.productcatalog.domain.model.Product;
import mk.ukim.finki.emt.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.productcatalog.port.request.ProductCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductCatalogController {

    private final ProductCatalog productCatalog;

    public ProductCatalogController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping
    public Page<Product> getProducts(Pageable pageable) {
        return productCatalog.findPaged(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return productCatalog.findById(new ProductId(id)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product create(@RequestBody @Valid ProductCreateRequest request) {
        return productCatalog.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.productCatalog.deleteById(new ProductId(id));
    }
}
