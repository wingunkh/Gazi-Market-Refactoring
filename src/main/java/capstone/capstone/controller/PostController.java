package capstone.capstone.controller;
import capstone.capstone.domain.Posts;
import capstone.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = {"http://52.78.130.186:3000"}) // CORS 문제를 해결하기 위한 어노테이션
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
public class PostController {
    @Autowired // 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    private PostService postService;

    //글 목록의 데이터를 리턴
    @GetMapping("/post") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<Posts> getAllPost() {
        return postService.getAllPost();
    }

    //글을 저장
    @PostMapping("/post") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public Posts createPost(@RequestBody Posts post) {
        System.out.println(post);
        post.setUpdateat(LocalDateTime.now());
        return postService.createPost(post);
    }

    //특정 게시글을 리턴
    @GetMapping("/posts/{no}")
    public ResponseEntity<Posts> getPostByNo(
            @PathVariable Integer no) {
        return postService.getPost(no);
    }

    //최신 순으로 정렬된 게시글 목록 리턴
    @GetMapping("/post/date")
    public List<Posts> getDatePost() {return postService.getDatePost();}

    //해당 카테고리 내의 게시글을 리턴
    @GetMapping("post/{category}")
    public List<Posts> getPostByCategory(@PathVariable String category){
        return postService.getCategoryPosts(category);
    }
}