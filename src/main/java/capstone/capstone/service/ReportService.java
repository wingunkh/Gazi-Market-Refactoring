package capstone.capstone.service;

import capstone.capstone.domain.ReportList;
import capstone.capstone.dto.ReportListResponse;
import capstone.capstone.repository.PictureRepository;
import capstone.capstone.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private FileHandler fileHandler;

    public void reportPost(Integer reporterNum, Integer postNum) {
        ReportList reportList = new ReportList(reporterNum, postNum, LocalDateTime.now().plusHours(9));

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

    public List<ReportListResponse> getAllReportList() {
        List<ReportListResponse> allReports = new ArrayList<>();
        List<ReportList> list = reportRepository.getAllReportList();

        for(ReportList reportList : list) {
            String nickname = memberService.findById(reportList.getReporterNum()).getNickname();
            ReportListResponse reportListResponse = new ReportListResponse(reportList, nickname);

            allReports.add(reportListResponse);
        }

        return allReports;
    }

    public void deleteReportedPost(Integer report_num) {
        Integer post_num = reportRepository.getPostNumByReportNum(report_num);
        String pictureUrl = pictureRepository.findByPostPostNum(post_num).getLocation();

        fileHandler.deleteFromS3(pictureUrl);
        reportRepository.deleteReportedPost(report_num);
        reportRepository.deleteReportList(report_num);
    }
}