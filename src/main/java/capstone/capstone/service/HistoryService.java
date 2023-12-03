package capstone.capstone.service;

import capstone.capstone.domain.LikeHistory;
import capstone.capstone.domain.VisitHistory;
import capstone.capstone.repository.LikeHistoryRepository;
import capstone.capstone.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final LikeHistoryRepository likeHistoryRepository;

    private final VisitHistoryRepository visitHistoryRepository;

    public LikeHistory saveLikeHistory(Integer memberNum, Integer postNum) {
        return likeHistoryRepository.save(new LikeHistory(memberNum, postNum));
    }

    public List<LikeHistory> findAllLikeHistory(Integer memberNum) {
        return likeHistoryRepository.findAllByMemberNum(memberNum);
    }

    @Transactional
    public String deleteLikeList(Integer memberNum, Integer postNum) {
        likeHistoryRepository.delete(new LikeHistory(memberNum, postNum));

        return "즐겨찾기 취소 완료";
    }

    public void visit(Integer postNum, Integer memberNum) {
       visitHistoryRepository.save(new VisitHistory(postNum, memberNum));
    }

    public List<VisitHistory> findAllVisitHistory(Integer memberNum) {
        return visitHistoryRepository.findAllByMemberNum(memberNum);
    }

    @Transactional
    public String deleteVisitList(Integer memberNum, Integer postNum) {
        visitHistoryRepository.delete(new VisitHistory(memberNum, postNum));

        return "방문 기록 삭제 완료";
    }
}