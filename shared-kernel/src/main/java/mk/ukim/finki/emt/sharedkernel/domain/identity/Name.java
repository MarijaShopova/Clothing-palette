package mk.ukim.finki.emt.sharedkernel.domain.identity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Name implements ValueObject {

    @Column(nullable = false)
    private final String name;

    private Name() {
        this.name = "";
    }

    public Name(@NonNull String name) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
    }

    public static Name valueOf(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
