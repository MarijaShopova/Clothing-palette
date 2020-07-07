package mk.ukim.finki.emt.sharedkernel.domain.authentication;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Username implements ValueObject {

    @Column(nullable = false)
    private final String value;

    private Username() {
        this.value = "";
    }

    public Username(@NonNull String value) {
        this.value = Objects.requireNonNull(value, "Username must not be null");
    }

    public static Username valueOf(String name) {
        return new Username(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username name1 = (Username) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
