package mk.ukim.finki.emt.sharedkernel.domain.identity;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Embeddable
@Getter
public class Image implements ValueObject {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] image;

    public Image(byte[] image) {
        this.image = image;
    }
}
