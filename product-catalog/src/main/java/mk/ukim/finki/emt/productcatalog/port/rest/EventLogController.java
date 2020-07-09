package mk.ukim.finki.emt.productcatalog.port.rest;

import mk.ukim.finki.emt.sharedkernel.infra.eventlog.DomainEventLogService;
import mk.ukim.finki.emt.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event-log")
public class EventLogController {

    private final DomainEventLogService domainEventLogService;

    public EventLogController(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @GetMapping("/{lastProcessedId}")
    public ResponseEntity<List<StoredDomainEvent>> domainEvents(@PathVariable("lastProcessedId") long lastProcessedId) {
        return ResponseEntity.ok().body(domainEventLogService.retrieveLog(lastProcessedId));
    }
}
