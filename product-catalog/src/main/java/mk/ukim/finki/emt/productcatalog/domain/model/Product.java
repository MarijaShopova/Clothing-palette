package mk.ukim.finki.emt.productcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "products")
@Where(clause = "deleted=false")
public class Product extends AbstractEntity<ProductId> {

    @Version
    private Long version;

    @Embedded
    private Name name;

    private String material;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private Money price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Variant> variants;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Product() {
    }

    public Product(ProductId productId, Name name, String material, Brand brand, Category category, Money price) {
        super(productId);
        this.name = name;
        this.material = material;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.variants = new HashSet<>();
    }

    @Override
    public ProductId id() {
        return id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

    public List<Variant> addVariants(List<Variant> variants1) {
        variants.addAll(variants1);
        return variants1;
    }

    public Variant addVariant(Variant variant) {
        variants.add(variant);
        return variant;
    }

    public Quantity totalQuantity() {
        return variants.stream().map(Variant::getQuantity).reduce(new Quantity(0), Quantity::add);
    }
}
