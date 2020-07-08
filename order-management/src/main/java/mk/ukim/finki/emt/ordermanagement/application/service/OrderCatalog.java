package mk.ukim.finki.emt.ordermanagement.application.service;

import lombok.NonNull;
import lombok.var;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderCreated;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderItemAdded;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public OrderCatalog(OrderRepository orderRepository,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;

    }

    public OrderId createOrder(@NonNull Order order) {
        Objects.requireNonNull(order,"order must not be null");
        var newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreated(newOrder.id(),newOrder.getOrderedOn()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAdded(newOrder.id(),orderItem.id(),orderItem.getProductId(),orderItem.getQuantity(), orderItem.getVariantId(), Instant.now())));
        return newOrder.id();
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    @NonNull
    private Order toDomainModel(@NonNull Order order) {
        var o = new Order(Instant.now(), order.getCurrency(),
                toDomainModel(order.getBillingAddress()), order.getState());
       // order.getItems().forEach(item -> o.addItem(item.getProductId(), item.getQuantity(),item.getVariantId()));
        order.getItems().forEach(item -> o.addOrderItem(item));
        return o;
    }

    @NonNull
    private RecipientAddress toDomainModel(@NonNull RecipientAddress address) {
        return new RecipientAddress(address.getAddress(),address.getCity(), address.getCountry(),address.getFullName().getFirstName(),address.getFullName().getLastName());
    }




}
