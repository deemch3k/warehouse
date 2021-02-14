package pl.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dyplom.domain.order.CancellationReport;

public interface CancellationReportRepo extends JpaRepository<CancellationReport, Long> {
}
