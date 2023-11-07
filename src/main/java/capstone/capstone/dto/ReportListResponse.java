package capstone.capstone.dto;

import capstone.capstone.domain.ReportList;
import lombok.Getter;

@Getter
public class ReportListResponse extends ReportList {
    String nickname;

    public ReportListResponse(ReportList reportList, String nickname) {
        this.reportNum = reportList.getReportNum();
        this.reporterNum = reportList.getReporterNum();
        this.nickname = nickname;
        this.postNum = reportList.getPostNum();
        this.reportedDate = reportList.getReportedDate();
    }
}