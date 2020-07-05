package mk.ukim.finki.emt.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent, String> {
}
