package mk.ukim.finki.emt.ordermanagement.application.service.form;

import com.sun.istack.NotNull;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderForm implements Serializable {

    @NotNull
    private Currency currency;

    @Valid
    @NotNull
    private RecipientAddressForm billingAddress = new RecipientAddressForm();

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public RecipientAddressForm getBillingAddress() {
        return billingAddress;
    }

    public List<OrderItemForm> getItems() {
        return items;
    }
}

