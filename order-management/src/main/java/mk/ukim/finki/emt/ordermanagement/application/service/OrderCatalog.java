package mk.ukim.finki.emt.ordermanagement.application.service;

import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderItemAdded;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderItemDeleted;
import mk.ukim.finki.emt.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderItemRepository;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.port.requests.OrderCreateRequest;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public OrderCatalog(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.applicationEventPublisher = applicationEventPublisher;

    }

    public Order createOrder(OrderCreateRequest request) {
        //saving order
        Order newOrder = new Order(
                request.getOrderId(),
                Currency.valueOf(request.getCurrency()),
                request.getRecipientAddress(),
                OrderState.PROCESSING,
                new UserId("1"));
        newOrder = orderRepository.saveAndFlush(newOrder);

        //saving orderItems
        List<OrderItem> orderItemList = request.getOrderItems()
                .stream()
                .map(orderItemCreateRequest -> {
                    OrderItem orderItem = new OrderItem(
                            orderItemCreateRequest.getOrderItemId(),
                            orderItemCreateRequest.getProductId(),
                            orderItemCreateRequest.getVariantId(),
                            orderItemCreateRequest.getPrice(),
                            orderItemCreateRequest.getQuantity());
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toList());

        // adding order items to order
        newOrder.addOrderItems(orderItemList);
        newOrder = orderRepository.save(newOrder);

        //publish events
        OrderId orderId = newOrder.id();
        newOrder.getItems().forEach(orderItem ->
                applicationEventPublisher.publishEvent(new OrderItemAdded(
                        orderId,
                        orderItem.id(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getVariantId(),
                        Instant.now())));

        //return new order
        return newOrder;
    }

    public void deleteOrderItem(OrderId orderId, OrderItemId orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(RuntimeException::new);
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        applicationEventPublisher.publishEvent(new OrderItemDeleted(orderId, orderItemId, orderItem.getProductId(), orderItem.getQuantity(), Instant.now(), orderItem.getVariantId()));
        order.removeOrderItem(orderItem);
        orderRepository.save(order);
        orderItemRepository.deleteById(orderItemId);
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void changeOrderState(String id, String state) {
        OrderState orderState = OrderState.valueOf(state);
        Order order = orderRepository.findById(new OrderId(id)).orElseThrow(RuntimeException::new);
        order.setOrderState(orderState);
        orderRepository.save(order);
    }
}
