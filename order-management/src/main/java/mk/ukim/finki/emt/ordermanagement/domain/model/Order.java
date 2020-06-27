package mk.ukim.finki.emt.ordermanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="orders")
public class Order extends AbstractEntity<OrderId> {

    @EmbeddedId
    private OrderId id;

    @Version
    private Long version;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderState state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress billingAddress;

    @Column(name = "ordered_on",nullable = false)
    private Instant orderedOn;

    @Override
    public OrderId id() {
        return id;
    }
}
