package mk.ukim.finki.emt.sharedkernel.domain.geo;

import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class RecipientAddress extends Address implements ValueObject {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false))})
    private FullName fullName;

    public RecipientAddress(@NotNull String address, @NotNull String city, @NotNull String country) {
        super(address, city, country);
    }
}
