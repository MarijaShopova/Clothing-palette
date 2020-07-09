package mk.ukim.finki.emt.ordermanagement.port.rest;

import mk.ukim.finki.emt.ordermanagement.application.service.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.port.requests.OrderCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController {

    private final OrderCatalog orderCatalog;

    public OrderCatalogController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
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

    @PatchMapping("/{idOrder}/{idOrderItem}")
    public void delete(@PathVariable String idOrder,@PathVariable String idOrderItem) {
        this.orderCatalog.deleteOrderItem(new OrderId(idOrder),new OrderItemId(idOrderItem));
    }
}
