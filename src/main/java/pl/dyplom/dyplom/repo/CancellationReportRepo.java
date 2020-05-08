package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dyplom.dyplom.domain.order.CancellationReport;

public interface CancellationReportRepo extends JpaRepository<CancellationReport, Long> {
}
