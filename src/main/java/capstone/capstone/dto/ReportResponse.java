package capstone.capstone.dto;

import capstone.capstone.domain.Report;
import lombok.Getter;

@Getter
public class ReportResponse extends Report {
    String nickname;

    public ReportResponse(Report report, String nickname) {
        this.reportNum = report.getReportNum();
        this.reporterNum = report.getReporterNum();
        this.nickname = nickname;
        this.postNum = report.getPostNum();
        this.reportedDate = report.getReportedDate();
    }
}