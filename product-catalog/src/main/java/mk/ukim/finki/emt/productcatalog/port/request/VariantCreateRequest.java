package mk.ukim.finki.emt.productcatalog.port.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.model.VariantId;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

@Getter
public class VariantCreateRequest {

    @NotNull
    VariantId variantId;

    @NotNull
    String color;

    @NotNull
    String size;

    @NotNull
    Quantity quantity;
}
