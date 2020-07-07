package mk.ukim.finki.emt.ordermanagement.application.service.form;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RecipientAddressForm implements Serializable {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String country;


}
