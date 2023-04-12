package capstone.capstone.controller;

import capstone.capstone.domain.Report_list;
import capstone.capstone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // 신고 목록 저장
    @PostMapping("/post/report/{post_num}/{reporter_num}")
    public void createReportList(@PathVariable Integer post_num, @PathVariable Integer reporter_num) {
        reportService.createReportList(post_num, reporter_num);
    }

    // 해당 신고 게시글 숨김 처리
    @PostMapping("/report/{report_num}/hide")
    public void hideReportList(@PathVariable Integer report_num) {
        reportService.hideReportList(report_num);
    }

    // 해당 숨김 처리된 게시글 공개 처리
    @PostMapping("/report/{report_num}/exposure")
    public void exposureReportList(@PathVariable Integer report_num) {
        reportService.exposureReportList(report_num);
    }

    // 해당 신고 게시글 삭제 처리
    @PostMapping("/report/{report_num}/delete")
    public void deleteReportList(@PathVariable Integer report_num) {
        reportService.deleteReportList(report_num);
    }

    // 신고 목록 데이터 리턴
    @GetMapping("/report")
    public List<Report_list> getAllReportList() {
        return reportService.getAllReportList();
    }
}
