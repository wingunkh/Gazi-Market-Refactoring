package capstone.capstone.controller;

import capstone.capstone.domain.Location;
import capstone.capstone.dto.PostResponse;
import capstone.capstone.domain.Post;
import capstone.capstone.service.ListService;
import capstone.capstone.service.MemberService;
import capstone.capstone.service.ModelService;
import capstone.capstone.service.PostService;;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    private final ModelService modelService;

    private final MemberService memberService;

    private final ListService listService;

    // 게시글 저장 (리액트)
    @PostMapping("/react")
    public ResponseEntity<Post> save(
            @RequestPart(value = "post") Post post,
            // HTTP Request Body에 multipart/form-data가 포함되어 있는 경우 사용
            @RequestPart(value = "file") MultipartFile file
    ) throws Exception {
        return ResponseEntity.ok(postService.save(post, file));
    }

    // 게시글 저장 (리액트 네이티브)
    @PostMapping
    public ResponseEntity<Post> save(
            @RequestPart(value = "modelName")
            String modelName,
            @RequestPart(value = "postTitle")
            String postTitle,
            @RequestPart(value = "postContent")
            String postContent,
            @RequestPart(value = "grade")
            String grade,
            @RequestPart(value = "price")
            String price,
            @RequestPart(value = "status")
            String status,
            @RequestPart(value = "memberNum")
            String memberNum,
            @RequestPart(value = "file")
            MultipartFile file
    ) throws Exception {
        Post post = Post.builder()
                .model(modelService.findById(modelName))
                .postTitle(postTitle)
                .postContent(postContent)
                .grade(grade)
                .price(Integer.parseInt(price))
                .status(status)
                .writtenDate(LocalDateTime.now())
                .member(memberService.findById(Integer.parseInt(memberNum)))
                .build();

        return ResponseEntity.ok(postService.save(post, file));
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    // 해당 게시글 조회
    @GetMapping("/{postNum}/{memberNum}")
    public ResponseEntity<PostResponse> findById(@PathVariable Integer postNum, @PathVariable Integer memberNum) {
        listService.visit(postNum, memberNum);

        return ResponseEntity.ok(postService.findById(postNum));
    }

    // 해당 게시글 수정
    @PatchMapping
    public ResponseEntity<String> update(@RequestBody Post post) {
        return ResponseEntity.ok(postService.update(post));
    }

    // 해당 게시글 삭제
    @DeleteMapping("/{postNum}")
    public ResponseEntity<String> delete(@PathVariable Integer postNum) {
        return ResponseEntity.ok(postService.delete(postNum));
    }

    // 오늘 작성된 게시글 전제 조회
    @GetMapping("/today")
    public ResponseEntity<List<PostResponse>> findAllByWrittenDate() {
        return ResponseEntity.ok(postService.findAllByWrittenDate());
    }

    // 해당 모델 내 게시글 전체 조회
    @GetMapping("/model/{modelName}")
    public ResponseEntity<List<PostResponse>> findAllByModelModelName(@PathVariable String modelName) {
        return ResponseEntity.ok(postService.findAllByModelModelName(modelName));
    }

    // 키워드로 게시글 검색
    @GetMapping("/search/{keyWord}")
    public ResponseEntity<List<PostResponse>> searchByKeyword(@PathVariable String keyWord) {
        return ResponseEntity.ok(postService.findByPostTitleContainingOrPostContentContainingOrderByWrittenDateDesc(keyWord));
    }

    // 해당 위치에서 해당 반경 내 게시글 전체 조회 (위도/경도/km)
    @GetMapping("/location/{latitude}/{longitude}/{distance}")
    public ResponseEntity<List<PostResponse>> findNearby(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double distance) {
        return ResponseEntity.ok(postService.findAllByLatitudeBetweenAndLongitudeBetween(latitude, longitude, distance));
    }

    // 해당 게시글 거래 위치 조회
    @GetMapping("/location/{postNum}")
    public ResponseEntity<Location> findLocation(@PathVariable Integer postNum) {
        return ResponseEntity.ok(postService.findLocation(postNum));
    }

    // 해당 사용자의 판매 완료 게시글 전체 조회
    @GetMapping("/soldOut/{memberNum}")
    public ResponseEntity<List<PostResponse>> findSoldOut(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(postService.findAllByMemberMemberNumAndStatusOrderByWrittenDate(memberNum));
    }

    // 해당 게시글 판매 완료 처리
    @PatchMapping("/soldOut/{postNum}")
    public ResponseEntity<String> soldOut(@PathVariable Integer postNum) {
        return ResponseEntity.ok(postService.soldOut(postNum));
    }
}