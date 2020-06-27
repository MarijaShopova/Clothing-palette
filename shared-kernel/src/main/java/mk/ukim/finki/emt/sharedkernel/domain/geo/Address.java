package mk.ukim.finki.emt.sharedkernel.domain.geo;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Embeddable
@Getter
@MappedSuperclass
public class Address implements ValueObject {

    @Column(name = "address")
    private final String address;

    @Column(name="city")
    private final String city;

    @Column(name="country")
    private final String country;

    public Address(@NotNull String address, @NotNull String city, @NotNull String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(country, address1.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address, city, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
