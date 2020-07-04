package mk.ukim.finki.emt.sharedkernel.domain.identity;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@Embeddable
public class FullName implements ValueObject {

    @Embedded
    private final Name firstName;

    @Embedded
    private final Name lastName;

    public FullName(Name firstName, Name lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static FullName valueOf(String firstName, String lastName) {
        return new FullName(Name.valueOf(firstName), Name.valueOf(lastName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(lastName, fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
