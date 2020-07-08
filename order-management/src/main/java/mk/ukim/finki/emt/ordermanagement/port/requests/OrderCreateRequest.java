package mk.ukim.finki.emt.ordermanagement.port.requests;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;

import java.util.List;

@Getter
public class OrderCreateRequest {

    @NotNull
    OrderId orderId;

    @NotNull
    String currency;

    @NotNull
    RecipientAddress recipientAddress;

    List<OrderItemCreateRequest> orderItems;
}
