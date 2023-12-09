package capstone.capstone.controller;

import capstone.capstone.domain.LikeHistory;
import capstone.capstone.domain.VisitHistory;
import capstone.capstone.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService historyService;

    // 해당 게시글 즐겨찾기
    @PostMapping("/like/{memberNum}/{postNum}")
    public ResponseEntity<LikeHistory> saveLikeHistory(@PathVariable Integer memberNum, @PathVariable Integer postNum) {
        return ResponseEntity.ok(historyService.saveLikeHistory(memberNum, postNum));
    }

    // 해당 사용자의 즐겨찾기 기록 전체 조회
    @GetMapping("/like/{memberNum}")
    public ResponseEntity<List<LikeHistory>> findAllLikeHistory(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(historyService.findAllLikeHistory(memberNum));
    }

    // 해당 사용자의 해당 게시글 즐겨찾기 취소
    @DeleteMapping("/like/{memberNum}/{postNum}")
    public ResponseEntity<String> deleteLikeList(@PathVariable Integer memberNum, @PathVariable Integer postNum) {
        return ResponseEntity.ok(historyService.deleteLikeList(memberNum, postNum));
    }

    // 해당 사용자의 방문 기록 전체 조회
    @GetMapping("/visit/{memberNum}")
    public ResponseEntity<List<VisitHistory>> findAllVisitHistory(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(historyService.findAllVisitHistory(memberNum));
    }

    // 해당 사용자의 해당 방문 기록 삭제
    @DeleteMapping("/visit/{memberNum}/{postNum}")
    public ResponseEntity<String> deleteVisitHistory(@PathVariable Integer memberNum, @PathVariable Integer postNum) {
        return ResponseEntity.ok(historyService.deleteVisitList(memberNum, postNum));
    }
}