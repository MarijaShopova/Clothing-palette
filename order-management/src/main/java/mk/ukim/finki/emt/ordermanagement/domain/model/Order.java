package mk.ukim.finki.emt.ordermanagement.domain.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="orders")
public class Order {

    @EmbeddedId
    private OrderId id;

    @Version
    private Long version;

    @Column(name = "ordered_on",nullable = false)
    private Instant orderedOn;

}
