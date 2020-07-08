package mk.ukim.finki.emt.productcatalog.port.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;

import java.util.List;

@Getter
public class ProductCreateRequest {

    @NotNull
    ProductId productId;

    @NotNull
    Name name;

    @NotNull
    String material;

    @NotNull
    String brand;

    @NotNull
    String category;

    @NotNull
    Money price;

    List<VariantCreateRequest> variants;
}
