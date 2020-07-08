package mk.ukim.finki.emt.ordermanagement.port.rest;

import mk.ukim.finki.emt.ordermanagement.application.service.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
