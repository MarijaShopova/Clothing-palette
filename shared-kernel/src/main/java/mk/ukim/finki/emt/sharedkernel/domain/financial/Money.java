package mk.ukim.finki.emt.sharedkernel.domain.financial;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final int amount;

    public Money(@NotNull Currency currency, @NotNull int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    private Money() {
        this.currency=null;
        this.amount = 0;
    }

    public static Money valueOf(Currency currency, int amount) {
        return new Money(currency, amount);
    }

    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("different currency");
        }
        return new Money(currency, amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("different currency");
        }
        return new Money(currency, amount - money.amount);
    }

    public Money multiply(int q) {
        return new Money(currency, amount * q);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return String.format("%s", "%d", currency, amount);
    }
}
