package capstone.capstone.extendedDomain;

import capstone.capstone.domain.ReportList;

public class ReportListWithName extends ReportList {
    String nickname;

    public ReportListWithName(ReportList reportList) {
        this.reportNum = reportList.getReportNum();
        this.reporterNum = reportList.getReporterNum();
        this.postNum = reportList.getPostNum();
        this.reportedDate = reportList.getReportedDate();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}