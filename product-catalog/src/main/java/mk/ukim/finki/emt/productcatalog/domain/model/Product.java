package mk.ukim.finki.emt.productcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Image;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "products")
public class Product extends AbstractEntity<ProductId> {

    @EmbeddedId
    private ProductId id;

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

    @Embedded
    private Image image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Variant> variants;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Product() {
    }

    public Product(ProductId productId, Name name, String material, Brand brand, Category category, Money price, Image image) {
        super(productId);
        this.name = name;
        this.material = material;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public Product(Name name, String material, Brand brand, Category category, Money price, Image image) {
        this.name = name;
        this.material = material;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    @Override
    public ProductId id() {
        return id;
    }
}
