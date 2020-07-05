package mk.ukim.finki.emt.sharedkernel.domain.base;

import mk.ukim.finki.emt.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}

