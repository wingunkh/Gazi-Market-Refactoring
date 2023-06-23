package capstone.capstone.service;

import capstone.capstone.domain.Report_list;
import capstone.capstone.extendedDomain.ReportListWithName;
import capstone.capstone.repository.PictureRepository;
import capstone.capstone.repository.ReportRepository;
import capstone.capstone.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserMemberRepository userMemberRepository;

    @Autowired
    private FileHandler fileHandler;

    public List<ReportListWithName> getAllReportList() {
        List<ReportListWithName> allReports = new ArrayList<ReportListWithName>();

        List<Report_list> list = reportRepository.getAllReportList();
        for(Report_list reportList : list) {
            ReportListWithName reportListWithName = new ReportListWithName(reportList);
            reportListWithName.setNickname(userMemberRepository.getNickname(reportList.getReporter_num()));

            allReports.add(reportListWithName);
        }

        return allReports;
    }

    public void reportPost(Integer reporter_num, Integer post_num) {
        Report_list reportList = new Report_list(reporter_num, post_num, LocalDateTime.now().plusHours(9));
        reportRepository.save(reportList);
    }

    public void hideReportedPost(Integer report_num) {
        reportRepository.hideReportedPost(report_num);
        reportRepository.deleteReportList(report_num);
    }

    public void exposureReportedPost(Integer report_num) {
        reportRepository.exposureReportedPost(report_num);
        reportRepository.deleteReportList(report_num);
    }

    public void deleteReportedPost(Integer report_num) {
        Integer post_no = reportRepository.getPostNoByReportNum(report_num);

        List<String> list = pictureRepository.getPictureLocation(post_no);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        reportRepository.deleteReportedPost(report_num);
        reportRepository.deleteReportList(report_num);
    }
}