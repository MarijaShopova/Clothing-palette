package mk.ukim.finki.emt.productcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "variants")
public class Variant extends AbstractEntity<VariantId> {

    @EmbeddedId
    private VariantId id;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @Enumerated(value = EnumType.STRING)
    private Size size;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))})
    private Quantity quantity;

    @Override
    public VariantId id() {
        return id;
    }
}
