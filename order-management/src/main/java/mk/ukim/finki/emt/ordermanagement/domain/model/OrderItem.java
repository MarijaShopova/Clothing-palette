package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "order_items")
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    private ProductId productId;

    @Embedded
    private VariantId variantId;

    @Embedded
    private Money price;

    @Embedded
    @Column(name = "qty", nullable = false)
    private Quantity quantity;

    public OrderItem() {

    }

    protected OrderItem(@NonNull ProductId productId, @NonNull VariantId variantId, @NonNull Money price, @NonNull Quantity quantity) {
        this.productId = productId;
        this.variantId = variantId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public OrderItemId id() {
        return id;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public void setVariantId(VariantId variandId) {
        this.variantId = variandId;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Money subtotal() {
        return price.multiply(quantity.getValue());
    }
}

