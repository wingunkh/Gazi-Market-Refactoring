package capstone.capstone.controller;

import capstone.capstone.domain.Report_list;
import capstone.capstone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://52.78.130.186:3000")
@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // 신고 목록 저장
    @GetMapping("/post/report/{post_num}/{reporter_num}")
    public void createReportList(@PathVariable Integer post_num, @PathVariable Integer reporter_num) {
        reportService.createReportList(post_num, reporter_num);
    }

    // 해당 신고 게시글 숨김 처리
    @GetMapping("/report/{report_num}/hide")
    public void hideReportedPost(@PathVariable Integer report_num) {
        reportService.hideReportedPost(report_num);
    }

    // 해당 숨김 처리된 게시글 공개 처리
    @PostMapping("/report/{report_num}/exposure")
    public void exposureReportedPost(@PathVariable Integer report_num) {
        reportService.exposureReportedPost(report_num);
    }

    // 해당 신고 게시글 삭제 처리
    @GetMapping("/report/{report_num}/delete")
    public void deleteReportedPost(@PathVariable Integer report_num) {
        reportService.deleteReportedPost(report_num);
    }

    // 신고 목록 데이터 리턴
    @GetMapping("/report")
    public List<Report_list> getAllReportList() {
        return reportService.getAllReportList();
    }
}
