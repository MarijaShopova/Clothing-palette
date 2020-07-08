package mk.ukim.finki.emt.productcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "variants")
public class Variant extends AbstractEntity<VariantId> {

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @Enumerated(value = EnumType.STRING)
    private Size size;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))})
    private Quantity quantity;

    private Variant() {
    }

    public Variant(Color color, Size size, int quantity) {
        this.color = color;
        this.size = size;
        this.quantity = Quantity.valueOf(quantity);
    }

    public Variant(VariantId variantId, Color color, Size size, Quantity quantity) {
        super(variantId);
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    @Override
    public VariantId id() {
        return id;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = Quantity.valueOf(quantity);
    }

    public Quantity reduceQuantity(Quantity reduceValue) {
        this.quantity = this.quantity.subtract(reduceValue);
        return this.quantity;
    }

    public Quantity increaseQuantity(Quantity increaseValue) {
        this.quantity = this.quantity.add(increaseValue);
        return this.quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Variant{");
        sb.append("color=").append(color);
        sb.append(", size=").append(size);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
