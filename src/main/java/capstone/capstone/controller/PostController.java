package capstone.capstone.controller;

import capstone.capstone.domain.Posts;
import capstone.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // CORS 문제를 해결하기 위한 어노테이션
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
public class PostController {
    @Autowired // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입, 생성자가 하나인 경우 입력하지 않아도 된다.
    private PostService postService;

    //Service를 호출해서 글 목록의 데이터를 리턴하는 메소드
    @GetMapping("/post") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<Posts> getAllPost() {
        return postService.getAllPost();
    }




    //Service를 호출해서 글을 저장하는 메소드
    @PostMapping("/post") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public Posts createPost(@RequestBody Posts post) {
        System.out.println(post);
        return postService.createPost(post);
    }

    //Service를 호출해서 특정 게시글을 리턴하는 메소드
    @GetMapping("/posts/{no}")
    public ResponseEntity<Posts> getPostByNo(
            @PathVariable Integer no) {
        return postService.getPost(no);
    }
}