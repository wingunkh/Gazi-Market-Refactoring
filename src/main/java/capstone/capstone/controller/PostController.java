package capstone.capstone.controller;

import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.PostWithPicture;
import capstone.capstone.domain.Post;
import capstone.capstone.service.PostService;;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000") // CORS 문제를 해결하기 위한 어노테이션
@CrossOrigin(origins = "http://52.78.130.186:80")
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
public class PostController {
    @Autowired // 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    private PostService postService;

    //글 목록의 데이터를 리턴
    @GetMapping("/post") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<post_addname> getAllPosts() throws IOException {
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getAllPosts()){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }

    //승인 대기글 목록의 데이터를 리턴
    @GetMapping("/approval")
    public List<post_addname> getAllWaitingApprovalPosts() throws IOException {
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getAllWaitingApprovalPosts()){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }

    //글을 저장
    @PostMapping("/post") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public Post createPost(
            @RequestPart(value = "post", required = false) // multipart/form-data에 특화되어 여러 복잡한 값을 처리할 때 사용할 수 있는 어노테이션이다.
            Post post,
            @RequestPart(value = "files") // 쿼리 파라미터, 폼 데이터, Multipart 등 많은 요청 파라미터를 처리할 수 있는 어노테이션이다.
            List<MultipartFile> files
    ) throws Exception {

        System.out.println(post);
        System.out.println("--------------------");
        System.out.println(files);
        post.setWritten_date(LocalDateTime.now());
        return postService.createPost(post, files);
    }

    @PostMapping("/post/native")
    public Post createPost(
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
        Post post = new Post();
        post.setModel_name(model_name);
        post.setUser_num(Integer.parseInt(user_no));
        post.setGrade(grade);
        post.setStatus(status);
        post.setPrice(Integer.parseInt(price));
        post.setPost_title(post_title);
        post.setPost_content(post_content);
        post.setWritten_date(LocalDateTime.now());

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

    //특정 승인 대기 게시글과 게시글의 사진 리턴
    @GetMapping("/approval/{no}")
    public PostWithPicture getWaitingApprovalPostByNo(@PathVariable Integer no) throws IOException {
        return postService.getPost(no);
    }

    //승인 대기 게시글 승인
    @GetMapping("/approval/{no}/{model_name}")
    public void approvePost(@PathVariable Integer no, @PathVariable String model_name) {
        postService.approvePost(no, model_name);
    }

    //승인 대기 게시글 거절
    @GetMapping("/approval/{no}/reject")
    public void rejectPost(@PathVariable Integer no) {
        postService.rejectPost(no);
    }

    //최신 순으로 정렬된 게시글 목록 리턴
    @GetMapping("/post/date")
    public List<post_addname> getDatePost() {
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getDatePost()){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }

    //해당 카테고리 내의 게시글을 리턴
    @GetMapping("post/category/{category}")
    public List<post_addname> getPostByCategory(@PathVariable String category){
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getCategoryPosts(category)){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }

    //해당 모델의 게시글을 리턴
    @GetMapping("post/model/{model}")
    public List<post_addname> getPostByModel(@PathVariable String model) {
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getModelPosts(model)){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }

    @GetMapping("post/name/{type}/{name}")  //type에는 무조건 asc OR desc로, asc:오름차순, desc:내림차순
    public List<post_addname> getPostByName(@PathVariable String type, @PathVariable String name) {
        List<post_addname> postAddnames = new ArrayList<>();
        for (Post post : postService.getNamePosts(type, name)){
            postAddnames.add(new post_addname(post));
        }
        return postAddnames;
    }


    @Getter
    @Setter
    class post_addname{
        private Integer post_num;
        private Integer user_num;
        private String model_name;
        private String grade;
        private String status;
        private Integer price;
        private String post_title;
        private String post_content;
        private LocalDateTime written_date;
        private String user_name;

        public post_addname(Post post) {
            this.post_num = post.getPost_num();
            this.user_num = post.getUser_num();
            this.model_name = post.getModel_name();
            this.grade = post.getGrade();
            this.status = post.getStatus();
            this.price = post.getPrice();
            this.post_title = post.getPost_title();
            this.post_content = post.getPost_content();
            this.written_date = post.getWritten_date();
            this.user_name = postService.getPost_Host_info(post.getPost_num());
        }
    }
}