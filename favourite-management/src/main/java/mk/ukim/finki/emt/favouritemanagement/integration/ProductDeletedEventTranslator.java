package mk.ukim.finki.emt.favouritemanagement.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDeletedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ProductDeletedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("mk.ukim.finki.emt.productcatalog.domain.event.ProductDeletedEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ProductDeletedEvent.class));
    }
}
