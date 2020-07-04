package mk.ukim.finki.emt.sharedkernel.domain.base;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredOn();
}
