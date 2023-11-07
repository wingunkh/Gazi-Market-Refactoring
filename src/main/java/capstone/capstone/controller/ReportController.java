package capstone.capstone.controller;

import capstone.capstone.dto.ReportListResponse;
import capstone.capstone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // 게시글 신고
    @GetMapping("/post/report/{reporter_num}/{post_num}")
    public void reportPost(@PathVariable Integer reporter_num, @PathVariable Integer post_num) {
        System.out.println(reporter_num + "번 고객이 " + post_num + "번 게시글 신고");
        reportService.reportPost(reporter_num, post_num);
    }

    // 전체 신고 목록 리턴
    @GetMapping("/report")
    public List<ReportListResponse> getAllReportList() {
        System.out.println("전체 신고 목록 반환");
        return reportService.getAllReportList();
    }

    // 해당 신고 게시글 숨김 처리
    @GetMapping("/report/{report_num}/hide")
    public void hideReportedPost(@PathVariable Integer report_num) {
        System.out.println(report_num + "번 신고, 해당 게시글 비공개 처리");
        reportService.hideReportedPost(report_num);
    }

    // 해당 숨김 처리된 게시글 공개 처리
    @GetMapping("/report/{report_num}/exposure")
    public void exposureReportedPost(@PathVariable Integer report_num) {
        System.out.println(report_num + "번 신고, 해당 게시글 공개 처리");
        reportService.exposureReportedPost(report_num);
    }

    // 해당 신고 게시글 삭제 처리
    @GetMapping("/report/{report_num}/delete")
    public void deleteReportedPost(@PathVariable Integer report_num) {
        System.out.println(report_num + "번 신고, 해당 게시글 삭제 처리");
        reportService.deleteReportedPost(report_num);
    }
}