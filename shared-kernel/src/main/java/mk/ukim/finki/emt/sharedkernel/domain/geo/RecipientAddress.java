package mk.ukim.finki.emt.sharedkernel.domain.geo;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;

import javax.persistence.*;

@Embeddable
@Getter
public class RecipientAddress extends Address implements ValueObject {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false))})
    private final FullName fullName;

    public RecipientAddress(@NotNull String address, @NotNull String city, @NotNull String country, Name firstName, Name lastName) {
        super(address, city, country);
        this.fullName = FullName.valueOf(firstName.getName(), lastName.getName());
    }

    public RecipientAddress() {
        this.fullName = new FullName();
    }

}
