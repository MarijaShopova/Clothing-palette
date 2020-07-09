package mk.ukim.finki.emt.ordermanagement.port.rest;

import com.sun.istack.NotNull;
import mk.ukim.finki.emt.ordermanagement.application.service.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.port.requests.OrderCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController {

    private final OrderCatalog orderCatalog;

    public OrderCatalogController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderCatalog.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return orderCatalog.findById(new OrderId(id)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createNewOrder(@RequestBody OrderCreateRequest request) {
        return orderCatalog.createOrder(request);
    }

    @DeleteMapping("/{id}/items/{orderItemId}")
    public void delete(@PathVariable String id,@PathVariable String orderItemId) {
        this.orderCatalog.deleteOrderItem(new OrderId(id),new OrderItemId(orderItemId));
    }

    @PutMapping("/{id}")
    public void updateOrderState(@PathVariable String id, @RequestBody @NotNull String state) {
        orderCatalog.changeOrderState(id, state);
    }
}
