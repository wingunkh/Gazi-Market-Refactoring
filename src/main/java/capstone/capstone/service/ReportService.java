package capstone.capstone.service;

import capstone.capstone.domain.Report_list;
import capstone.capstone.repository.PictureRepository;
import capstone.capstone.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private FileHandler fileHandler;

    public void createReportList(Integer post_num, Integer reporter_num) {
        Report_list reportList = new Report_list(post_num, reporter_num, LocalDateTime.now().plusHours(9));
        reportRepository.save(reportList);
    }

    public void hideReportedPost(Integer report_num) {
        reportRepository.hideReportedPost(report_num);
    }

    public void exposureReportedPost(Integer report_num) {
        reportRepository.exposureReportedPost(report_num);
    }

    public void deleteReportedPost(Integer report_num) {
        Integer post_no = reportRepository.getPostNoByReportNum(report_num);

        List<String> list = pictureRepository.getPictureLocationByPostNo(post_no);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        reportRepository.deleteReportedPost(report_num);
    }

    public List<Report_list> getAllReportList() {
        return reportRepository.getAllReportList();
    }
}
