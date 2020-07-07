package mk.ukim.finki.emt.productcatalog;

import mk.ukim.finki.emt.productcatalog.domain.model.*;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.domain.repository.VariantRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Image;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class DataGenerator {

    private final ProductRepository productRepository;
    private final VariantRepository variantRepository;

    DataGenerator(ProductRepository productRepository, VariantRepository variantRepository) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        Set<Variant> variants;
        if (variantRepository.findAll().size() == 0) {
            variants = new HashSet<>();
            variants.add(createVariant(new VariantId("1"), Color.BLACK, Size.S, 10));
            variants.add(createVariant(new VariantId("2"), Color.RED, Size.S, 10));
            variants.add(createVariant(new VariantId("3"), Color.GREEN, Size.XL, 10));
            variants.add(createVariant(new VariantId("4"), Color.WHITE, Size.XS, 10));
            variants.add(createVariant(new VariantId("5"), Color.BLACK, Size.S, 10));
            variantRepository.saveAll(variants);
  //    } else {
//          variants = new HashSet<>(variantRepository.findAll());
        }


        if (productRepository.findAll().size() == 0) {
            Set<Product> products = new HashSet<>();
            products.add(createProduct(new ProductId("1"), Name.valueOf("Shirt"), "cotton",
                    Brand.BERSHKA, Category.T_SHIRT, new Money(Currency.MKD, 500), variantRepository.findAll()));
            products.add(createProduct(new ProductId("2"), Name.valueOf("Skirt"), "silk",
                    Brand.ZARA, Category.SKIRT, new Money(Currency.MKD, 1500), variantRepository.findAll()));
            products.add(createProduct(new ProductId("3"), Name.valueOf("Pants"), "denim",
                    Brand.BERSHKA, Category.PANTS, new Money(Currency.MKD, 2500), variantRepository.findAll()));
            productRepository.saveAll(products);
        }

    }

    private Product createProduct(ProductId productId, Name name, String material, Brand brand,
                                  Category category, Money price, List<Variant> variants) {
        Product product = new Product(productId, name, material, brand, category, price);
        return product;
    }

    private Variant createVariant(VariantId variantId, Color color, Size size, int quantity) {
        return new Variant(variantId, color, size, quantity);
    }
}
