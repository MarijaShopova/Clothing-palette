package mk.ukim.finki.emt.productcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Image;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "products")
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

//    @Embedded
//    @AttributeOverrides({@AttributeOverride(name = "image", column = @Column(name = "image", nullable = true))})
//    private Image image = null;

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
      //  this.image = image;
    }

    public Product(Name name, String material, Brand brand, Category category, Money price) {
        this.name = name;
        this.material = material;
        this.brand = brand;
        this.category = category;
        this.price = price;
      //  this.image = image;
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

//    public void setImage(Image image) {
//        this.image = image;
//    }

    public Variant addVariant(Variant variant) {
        variants.add(variant);
        return variant;
    }

//    public Variant addVariant(Color color, Size size, int quantity) {
//        Variant variant = new Variant(color, size, quantity);
//        variants.add(variant);
//        return variant;
//    }

    public void reduceVariantQuantity(VariantId variantId, Quantity quantity) {
        this.variants.stream().filter(it -> it.id().equals(variantId)).forEach(it -> it.reduceQuantity(quantity));
    }

    public void increaseVariantQuantity(VariantId variantId, Quantity quantity) {
        this.variants.stream().filter(it -> it.id().equals(variantId)).forEach(it -> it.increaseQuantity(quantity));
    }

    public Quantity totalQuantity() {
        return variants.stream().map(Variant::getQuantity).reduce(new Quantity(0), Quantity::add);
    }
}
