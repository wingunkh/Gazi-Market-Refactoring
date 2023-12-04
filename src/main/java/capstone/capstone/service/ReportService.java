package capstone.capstone.service;

import capstone.capstone.domain.Member;
import capstone.capstone.domain.Report;
import capstone.capstone.repository.MemberRepository;
import capstone.capstone.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    private final MemberRepository memberRepository;

    public Report save(Integer reporterNum, Integer postNum) {
        Optional<Member> optionalMember = memberRepository.findById(reporterNum);

        if (optionalMember.isPresent()) {
            Report report = Report.builder()
                    .member(optionalMember.get())
                    .postNum(postNum)
                    .reportedDate(LocalDateTime.now())
                    .build();

            return reportRepository.save(report);
        } else
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}