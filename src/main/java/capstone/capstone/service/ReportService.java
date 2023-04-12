package capstone.capstone.service;

import capstone.capstone.domain.Report_list;
import capstone.capstone.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public void createReportList(Integer post_num, Integer reporter_num) {
        Report_list reportList = new Report_list(post_num, reporter_num, LocalDateTime.now(), "미처리");
        reportRepository.save(reportList);
    }

    public void hideReportList(Integer report_num) {
        reportRepository.hideReportList(report_num);
    }

    public void exposureReportList(Integer report_num) {
        reportRepository.exposureReportList(report_num);
    }

    public void deleteReportList(Integer report_num) {
        reportRepository.deleteReportList(report_num);
    }

    public List<Report_list> getAllReportList() {
        return reportRepository.getAllReportList();
    }
}
