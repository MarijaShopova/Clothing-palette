package mk.ukim.finki.emt.ordermanagement.port.requests;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.model.ProductId;
import mk.ukim.finki.emt.ordermanagement.domain.model.VariantId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

@Getter
public class OrderItemCreateRequest {

    @NotNull
    OrderItemId orderItemId;

    @NotNull
    ProductId productId;

    @NotNull
    VariantId variantId;

    @NotNull
    Money price;

    @NotNull
    Quantity quantity;
}
