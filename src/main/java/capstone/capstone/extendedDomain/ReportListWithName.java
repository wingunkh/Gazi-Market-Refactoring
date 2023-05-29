package capstone.capstone.extendedDomain;

import capstone.capstone.domain.Report_list;

public class ReportListWithName extends Report_list {
    String nickname;

    public ReportListWithName(Report_list reportList) {
        this.report_num = reportList.getReport_num();
        this.reporter_num = reportList.getReporter_num();
        this.post_num = reportList.getPost_num();
        this.report_date = reportList.getReport_date();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}