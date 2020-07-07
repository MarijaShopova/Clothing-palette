package mk.ukim.finki.emt.sharedkernel.domain.measurement;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Quantity implements ValueObject {

    private final int value;

    public Quantity(@NonNull int value) {
        checkNegative(value);
        this.value = value;
    }

    private Quantity() {
        this.value = 0;
    }

    public static Quantity valueOf(int value) {
        return new Quantity(value);
    }

    public Quantity add(Quantity otherQuantity) {
        checkNegative(otherQuantity.value);
        return new Quantity(value + otherQuantity.value);
    }

    public Quantity subtract(Quantity otherQuantity) {
        checkNegative(otherQuantity.value);
        checkNegative(value - otherQuantity.value);
        return new Quantity(value - otherQuantity.value);
    }

    private void checkNegative(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Quantity cannot be negative!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
