package capstone.capstone.controller;

import capstone.capstone.domain.Posts;
import capstone.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")     // CORS 문제를 해결하기 위해 추가
@RestController                                     // @Controller에 @ResponseBody가 결합, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api")                             // /api URL에서 PostController 실행됨
public class PostController {
    @Autowired                                      // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입, 생성자가 하나인 경우 입력하지 않아도 됨
    private PostService postService;

    //Service를 호출해서 글 목록의 데이터를 리턴하는 메소드
    // get all post
    @GetMapping("/post")                            // 어떠한 정보를 가져와서 조회하기 위해 사용되는 방식
    public List<Posts> getAllPosts() {
        return postService.getAllPost();
    }

    //Service를 호출해서 글을 저장하는 메소드
    // create post
    @PostMapping("/post")                           // 데이터를 서버로 제출하여 추가 또는 수정하기 위해서 데이터를 전송하는 방식
    public Posts createPost(@RequestBody Posts post) {
        return postService.createPost(post);
    }

    //get post
    @GetMapping("/post/{no}")
    public ResponseEntity<Posts> getPostByNo(
            @PathVariable Integer no) {

        return postService.getPost(no);
    }





}
