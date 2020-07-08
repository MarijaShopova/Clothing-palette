package mk.ukim.finki.emt.sharedkernel.infra.eventlog;


import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainEventLogService {

    private final StoredDomainEventRepository storedDomainEventRepository;
    private final ObjectMapper objectMapper;

    DomainEventLogService(StoredDomainEventRepository storedDomainEventRepository,
                          ObjectMapper objectMapper) {
        this.storedDomainEventRepository = storedDomainEventRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(@NonNull DomainEvent domainEvent) {
        StoredDomainEvent storedEvent = new StoredDomainEvent(domainEvent, objectMapper);
        storedDomainEventRepository.saveAndFlush(storedEvent);
    }

    @NonNull
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StoredDomainEvent> retrieveLog(long lastProcessedEventId) {
        Long max = storedDomainEventRepository.findHighestDomainEventId();
        if (max == null) {
            max = 0L;
        }
        return storedDomainEventRepository.findEventsBetween(lastProcessedEventId, max);
    }
}
