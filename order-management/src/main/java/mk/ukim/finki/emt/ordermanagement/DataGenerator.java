package mk.ukim.finki.emt.ordermanagement;

import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItem;
import mk.ukim.finki.emt.ordermanagement.domain.model.ProductId;
import mk.ukim.finki.emt.ordermanagement.domain.model.VariantId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderItemRepository;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
class DataGenerator {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    DataGenerator(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @PostConstruct
    @Transactional
    public void generateData() {
        Set<OrderItem> orderItems;
        if (orderItemRepository.findAll().size() == 0) {
            orderItems = new HashSet<>();
            orderItems.add(createOrderItem(new ProductId("1"), new VariantId("1"), new Money(Currency.MKD, 500),
                    new Quantity(1)));
            orderItemRepository.saveAll(orderItems);
        }



    }

    private OrderItem createOrderItem(ProductId productId, VariantId variantId, Money price, Quantity quantity) {
        return new OrderItem(productId,variantId,price,quantity);
    }

}
