package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.var;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderState state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress billingAddress;

    @Column(name = "ordered_on", nullable = false)
    private Instant orderedOn;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    public Order() {

    }

    public Order(Instant orderedOn, Currency currency, RecipientAddress billingAddress, OrderState orderState) {
        this.currency = currency;
        this.orderedOn = orderedOn;
        this.billingAddress = billingAddress;
        this.state = orderState;
    }

    public Order(OrderId orderId, Instant orderedOn, Currency currency, RecipientAddress billingAddress, OrderState orderState) {
        super(orderId);
        this.items = new HashSet<>();
        this.currency = currency;
        this.orderedOn = orderedOn;
        this.billingAddress = billingAddress;
        this.state = orderState;
    }

    @Override
    public OrderId id() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public OrderState getState() {
        return state;
    }

    public RecipientAddress getBillingAddress() {
        return billingAddress;
    }

    public Instant getOrderedOn() {
        return orderedOn;
    }


    public Set<OrderItem> getItems() {
        return items;
    }

    public Money total() {
        return items.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Product product, Quantity quantity, @NonNull Variant variant) {
        Objects.requireNonNull(product, "Product must not be null");
        var item = new OrderItem(product.getId(), variant.getId(), product.getPrice(), quantity);
        item.setQuantity(quantity);
        items.add(item);
        return item;
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        return orderItem;
    }

}
