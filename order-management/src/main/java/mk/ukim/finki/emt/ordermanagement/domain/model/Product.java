package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

@Getter
public class Product {

    private ProductId id;

    private Money price;

    private Quantity quantity;
}
