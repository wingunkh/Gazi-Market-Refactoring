package capstone.capstone.controller;

import capstone.capstone.domain.Member;
import capstone.capstone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // 사용자 저장
    @PostMapping
    public ResponseEntity<Member> save(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.save(member));
    }

    // 사용자 전체 조회
    @GetMapping
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 해당 사용자 조회
    @GetMapping("/{memberNum}")
    public Member findById(@PathVariable Integer memberNum) {
        return memberService.findById(memberNum);
    }

    // 해당 사용자 프로필 이미지 조회
    @GetMapping("/{memberNum}/profileImage")
    public ResponseEntity<String> getProfileImage(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(memberService.getProfileImage(memberNum));
    }

    // 해당 사용자 프로필 이미지 수정
    @PatchMapping
    // PATCH 메서드 : RequestBody를 통해 데이터 부분 수정
    public ResponseEntity<String> updateProfileImage(
            @RequestPart(value = "memberNum")
            String memberNum,
            @RequestPart(value = "file")
            MultipartFile file
    ) throws Exception {
        return ResponseEntity.ok(memberService.updateProfileImage(Integer.parseInt(memberNum), file));
    }
}