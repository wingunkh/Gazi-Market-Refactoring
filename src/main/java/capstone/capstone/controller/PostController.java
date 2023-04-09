package capstone.capstone.controller;

import capstone.capstone.domain.PostWithPicture;
import capstone.capstone.domain.Posts;
import capstone.capstone.service.PostService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // CORS 문제를 해결하기 위한 어노테이션
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
public class PostController {
    @Autowired // 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    private PostService postService;

    //글 목록의 데이터를 리턴
    @GetMapping("/post") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<Posts> getAllPost() throws IOException {
        return postService.getAllPost();
    }

    //글을 저장
    @PostMapping("/post") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public Posts createPost(
            @RequestPart(value = "post", required = false) // multipart/form-data에 특화되어 여러 복잡한 값을 처리할 때 사용할 수 있는 어노테이션이다.
            Posts post,
            @RequestPart(value = "files") // 쿼리 파라미터, 폼 데이터, Multipart 등 많은 요청 파라미터를 처리할 수 있는 어노테이션이다.
            List<MultipartFile> files
    ) throws Exception {

        System.out.println(post);
        System.out.println("--------------------");
        System.out.println(files);
        post.setUpdateat(LocalDateTime.now());
        return postService.createPost(post, files);
    }

    @PostMapping("/post/native")
    public Posts createPost(
            @RequestPart(value = "model_name")
            String model_name,
            @RequestPart(value = "user_no")
            String user_no,
            @RequestPart(value = "grade")
            String grade,
            @RequestPart(value = "status")
            String status,
            @RequestPart(value = "price")
            String price,
            @RequestPart(value = "post_title")
            String post_title,
            @RequestPart(value = "post_content")
            String post_content,
            @RequestPart(value = "files")
            List<MultipartFile> files
    ) throws Exception {
        Posts post = new Posts();
        post.setModel_name(model_name);
        post.setUser_no(Integer.parseInt(user_no));
        post.setGrade(grade);
        post.setStatus(status);
        post.setPrice(Integer.parseInt(price));
        post.setPost_title(post_title);
        post.setPost_content(post_content);
        post.setUpdateat(LocalDateTime.now());

        System.out.println(post);
        System.out.println("--------------------");
        System.out.println(files);
        return postService.createPost(post, files);
    }

    //특정 게시글과 게시글의 사진 리턴
    @GetMapping("/post/{no}")
    public PostWithPicture getPostByNo(@PathVariable Integer no) throws IOException {
        return postService.getPost(no);
    }

    //최신 순으로 정렬된 게시글 목록 리턴
    @GetMapping("/post/date")
    public List<Posts> getDatePost() {return postService.getDatePost();}

    //해당 카테고리 내의 게시글을 리턴
    @GetMapping("post/category/{category}")
    public List<Posts> getPostByCategory(@PathVariable String category){
        return postService.getCategoryPosts(category);
    }

    //해당 모델의 게시글을 리턴
    @GetMapping("post/model/{model}")
    public List<Posts> getPostByModel(@PathVariable String model) {
        return postService.getModelPosts(model);
    }

    @GetMapping("post/name/{type}/{name}")  //type에는 무조건 asc OR desc로, asc:오름차순, desc:내림차순
    public List<Posts> getPostByName(@PathVariable String type, @PathVariable String name) {
        return postService.getNamePosts(type, name); }
}