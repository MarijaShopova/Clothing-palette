package mk.ukim.finki.emt.ordermanagement.application.service.form;

import com.sun.istack.NotNull;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.model.Product;
import mk.ukim.finki.emt.ordermanagement.domain.model.Variant;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class OrderItemForm implements Serializable {

    @NotNull
    private Product product;

    @NonNull
    private Variant variant;

    @Min(1)
    private Quantity quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Variant  getVariant(){
        return variant;
    }

    public void setVariant(Variant variant){
        this.variant = variant;
    }
}

