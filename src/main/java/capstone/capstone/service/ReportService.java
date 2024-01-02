package capstone.capstone.service;

import capstone.capstone.domain.Report;
import capstone.capstone.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public Report saveReport(Integer reporterNum, Integer postNum) {
        Report report = Report.builder()
                .reporterNum(reporterNum)
                .postNum(postNum)
                .reportedDate(LocalDateTime.now())
                .build();

        return reportRepository.save(report);
    }

    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }
}