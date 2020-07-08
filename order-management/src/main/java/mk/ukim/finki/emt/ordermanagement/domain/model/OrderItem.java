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
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))})
    private ProductId productId;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "variant_id", nullable = false))})
    private VariantId variantId;

    @Embedded
    private Money price;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "qty", nullable = false))})
    private Quantity quantity;

    public OrderItem() {

    }
    public OrderItem(ProductId productId, VariantId variantId, Money price, Quantity quantity) {
        this.productId = productId;
        this.variantId = variantId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(OrderItemId orderItemId,ProductId productId, VariantId variantId, Money price, Quantity quantity) {
        super(orderItemId);
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

    public void setVariantId(VariantId variantId) {
        this.variantId = variantId;
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

