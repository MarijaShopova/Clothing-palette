package mk.ukim.finki.emt.productcatalog.application.service;

import mk.ukim.finki.emt.productcatalog.domain.model.Product;
import mk.ukim.finki.emt.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.domain.repository.VariantRepository;
import mk.ukim.finki.emt.productcatalog.integration.OrderItemAddedEvent;
import mk.ukim.finki.emt.productcatalog.integration.OrderItemDeletedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProductCatalog {

    private final ProductRepository repository;
    private final VariantRepository variantRepository;

    public ProductCatalog(ProductRepository repository, VariantRepository variantRepository) {
        this.repository = repository;
        this.variantRepository = variantRepository;
    }

    public Page<Product> findPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Product> findById(@NonNull ProductId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return repository.findById(productId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemAdded(OrderItemAddedEvent event) {
        Product product = repository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        product.reduceVariantQuantity(event.getVariantId(), event.getQuantity());
        repository.save(product);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderItemDeleted(OrderItemDeletedEvent event) {
        Product product = repository.findById(event.getProductId()).orElseThrow(RuntimeException::new);
        product.increaseVariantQuantity(event.getVariantId(), event.getQuantity());
        repository.save(product);
    }

}
