package capstone.capstone.controller;

import capstone.capstone.domain.Report;
import capstone.capstone.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    // 게시글 신고
    @PostMapping("/{reporterNum}/{postNum}")
    public ResponseEntity<Report> saveReport(@PathVariable Integer reporterNum, @PathVariable Integer postNum) {
        return ResponseEntity.ok(reportService.saveReport(reporterNum, postNum));
    }

    // 신고 기록 전체 조회
    @GetMapping
    public ResponseEntity<List<Report>> findAllReports() {
        return ResponseEntity.ok(reportService.findAllReports());
    }
}