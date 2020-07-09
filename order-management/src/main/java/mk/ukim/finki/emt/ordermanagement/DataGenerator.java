package mk.ukim.finki.emt.ordermanagement;

import mk.ukim.finki.emt.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderItemRepository;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
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
        Set<Order> orders;

        if (orderItemRepository.findAll().size() == 0) {
            orderItems = new HashSet<>();
            orderItems.add(createOrderItem(new OrderItemId("1"), new ProductId("1"), new VariantId("1"), new Money(Currency.MKD, 500), new Quantity(1)));
            orderItems.add(createOrderItem(new OrderItemId("2"), new ProductId("2"), new VariantId("2"), new Money(Currency.MKD, 1500), new Quantity(1)));
            orderItems.add(createOrderItem(new OrderItemId("3"), new ProductId("1"), new VariantId("2"), new Money(Currency.MKD, 800), new Quantity(3)));
            orderItems.add(createOrderItem(new OrderItemId("4"), new ProductId("3"), new VariantId("4"), new Money(Currency.MKD, 2000), new Quantity(2)));
            orderItemRepository.saveAll(orderItems);
        }

        if (orderRepository.findAll().size() == 0) {
            orders = new HashSet<>();
            orders.add(createOrder(new OrderId("1"), Currency.MKD, new RecipientAddress("Kata Pockova", "Strumica", "Makedonija", Name.valueOf("Ljubica"), Name.valueOf("Boneva")), OrderState.PROCESSING));
            orders.add(createOrder(new OrderId("2"), Currency.MKD, new RecipientAddress("Kata Pockova", "Strumica", "Makedonija", Name.valueOf("Marija"), Name.valueOf("Shopova")), OrderState.PROCESSING));
            orderRepository.saveAll(orders);

            addOrderItemToOrder("1","2");
            addOrderItemToOrder("1","4");
            addOrderItemToOrder("2","1");
            addOrderItemToOrder("2","3");
        }
    }

    private OrderItem createOrderItem(OrderItemId orderItemId, ProductId productId, VariantId variantId, Money price, Quantity quantity) {
        return new OrderItem(orderItemId, productId, variantId, price, quantity);
    }

    private Order createOrder(OrderId orderId, Currency currency, RecipientAddress address, OrderState orderState) {
        return new Order(orderId, currency, address, orderState);
    }

    private void addOrderItemToOrder(String orderId, String orderItemId) {
        Order order = orderRepository.findById(new OrderId(orderId)).get();
        OrderItem orderItem = orderItemRepository.findById(new OrderItemId(orderItemId)).get();
        order.addOrderItem(orderItem);
        orderRepository.save(order);
    }
}
