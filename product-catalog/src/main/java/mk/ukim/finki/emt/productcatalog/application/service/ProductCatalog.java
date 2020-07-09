package mk.ukim.finki.emt.productcatalog.application.service;

import mk.ukim.finki.emt.productcatalog.domain.event.ProductDeletedEvent;
import mk.ukim.finki.emt.productcatalog.domain.model.*;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.domain.repository.VariantRepository;
import mk.ukim.finki.emt.productcatalog.integration.OrderItemAdded;
import mk.ukim.finki.emt.productcatalog.integration.OrderItemDeleted;
import mk.ukim.finki.emt.productcatalog.port.request.ProductCreateRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCatalog {

    private final ProductRepository repository;
    private final VariantRepository variantRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductCatalog(ProductRepository repository,
                          VariantRepository variantRepository,
                          ApplicationEventPublisher applicationEventPublisher) {
        this.repository = repository;
        this.variantRepository = variantRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Page<Product> findPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Product> findById(@NonNull ProductId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return repository.findById(productId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemAdded(OrderItemAdded event) {
        Variant variant = variantRepository.findById(event.getVariantId()).orElseThrow(RuntimeException::new);;
        variant.reduceQuantity(event.getQuantity());
        variantRepository.save(variant);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemDeleted(OrderItemDeleted event) {
        Variant variant = variantRepository.findById(event.getVariantId()).orElseThrow(RuntimeException::new);
        variant.increaseQuantity(event.getQuantity());
        variantRepository.save(variant);
    }

    public void deleteById(ProductId productId) {
        this.repository.deleteById(productId);
        this.applicationEventPublisher.publishEvent(new ProductDeletedEvent(productId, Instant.now()));
    }

    public Product create(ProductCreateRequest request) {
        //saving product
        Product newProduct = new Product(
                request.getProductId(),
                request.getName(),
                request.getMaterial(),
                Brand.valueOf(request.getBrand()),
                Category.valueOf(request.getCategory()),
                request.getPrice());
        newProduct = repository.saveAndFlush(newProduct);

        //saving variants
        List<Variant> variants = request.getVariants()
                .stream()
                .map(variantCreateRequest -> {
                    Variant variant = new Variant(
                            variantCreateRequest.getVariantId(),
                            Color.valueOf(variantCreateRequest.getColor()),
                            Size.valueOf(variantCreateRequest.getSize()),
                            variantCreateRequest.getQuantity());
                    return variantRepository.save(variant);
                })
                .collect(Collectors.toList());

        // adding variants to product
        newProduct.addVariants(variants);

        return repository.save(newProduct);
    }
}
