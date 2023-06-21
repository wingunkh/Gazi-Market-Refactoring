package capstone.capstone.controller;

import capstone.capstone.domain.Location;
import capstone.capstone.extendedDomain.PostWithPicture;
import capstone.capstone.domain.Post;
import capstone.capstone.service.ListService;
import capstone.capstone.service.PostService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController // @Controller에 @ResponseBody가 결합한 어노테이션, 문자열과 JSON 등을 전송 가능
@RequestMapping("/api") // 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
public class PostController {
    @Autowired // 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입
    private PostService postService;

    @Autowired
    private ListService listService;

    // 게시글 저장 (리액트)
    @PostMapping("/post") // POST 방식: 특정 데이터를 서버로 제출하여 해당 데이터를 추가, 수정 또는 삭제하기 위해 데이터를 전송하는 방식
    public void createPost(
            @RequestPart(value = "post", required = false) // multipart/form-data에 특화되어 여러 복잡한 값을 처리할 때 사용할 수 있는 어노테이션
            Post post,
            @RequestPart(value = "files") // 쿼리 파라미터, 폼 데이터, Multipart 등 많은 요청 파라미터를 처리할 수 있는 어노테이션
            List<MultipartFile> files
    ) throws Exception {
        post.setWritten_date(LocalDateTime.now().plusHours(9));
        System.out.println(post.getUser_num() + "번 사용자 -> " + post.getModel_name() + " 판매 게시글 작성");
        postService.createPost(post, files);
    }

    // 게시글 저장 (리액트 네이티브)
    @PostMapping("/post/native")
    public void createPost(
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
        post.setWritten_date(LocalDateTime.now().plusHours(9));
        postService.createPost(post, files);
        System.out.println(post.getUser_num() + "번 사용자 -> " + post.getModel_name() + " 판매 게시글 작성");
    }

    // 전체 공개 게시글 목록 리턴
    @GetMapping("/post") // GET 방식: 정보를 단순히 조회하기 위해 사용하는 방식
    public List<PostWithPicture> getAllPosts() throws IOException {
        System.out.println("공개 게시글 목록 반환");
        return postService.getAllPosts();
    }

    // 해당 게시글 리턴
    @GetMapping("/post/{user_num}/{no}")
    public PostWithPicture getPostByNo(@PathVariable Integer user_num, @PathVariable Integer no) throws IOException {
        listService.addvisit(user_num, no);
        System.out.println(user_num + "번 사용자 " + no + "번 게시글 방문");
        return postService.getPost(no);
    }

    // 해당 카테고리 내 게시글 목록 리턴
    @GetMapping("post/category/{category}")
    public List<PostWithPicture> getPostByCategory(@PathVariable String category){
        List<PostWithPicture> postList = new ArrayList<>();
        for (Post post : postService.getCategoryPosts(category)){
            postList.add(postService.PostToPostWithPicture(post));
        }
        System.out.println(category + " 게시글 목록 반환");
        return postList;
    }

    // 해당 모델 내 게시글 목록 리턴
    @GetMapping("post/model/{model}")
    public List<PostWithPicture> getPostByModel(@PathVariable String model) {
        List<PostWithPicture> postList = new ArrayList<>();
        for (Post post : postService.getModelPosts(model)){
            postList.add(postService.PostToPostWithPicture(post));
        }
        System.out.println(model + " 게시글 목록 반환");
        return postList;
    }

    // 오늘 작성된 게시글 목록 리턴
    @GetMapping("/post/today")
    public List<PostWithPicture> getTodayPost(){
        System.out.println("오늘 작성된 게시글 목록 반환");
        return postService.getPost_Today();
    }

    // 해당 게시글 수정
    @PostMapping("/post/{post_num}/modify")
    public void updatePost(@PathVariable Integer post_num, @RequestBody Post post) throws Exception {
        post.setWritten_date(LocalDateTime.now().plusHours(9));
        System.out.println(post.getUser_num() + "번 사용자 " + post_num + "번 게시글 수정");
        postService.updatePost(post_num, post);
    }

    // 해당 게시글 삭제
    @GetMapping("/post/{num}/delete")
    public void deletePost(@PathVariable Integer num){
        System.out.println(num + "번 게시글 삭제");
        postService.deletePost(num);
    }

    // 게시글 제목으로 검색
    @GetMapping("post/name/{type}/{name}")
    public List<PostWithPicture> getPostByName(@PathVariable String type, @PathVariable String name) throws IOException {
        List<PostWithPicture> postList = new ArrayList<>();
        for (Post post : postService.getNamePosts(type, name)){
            postList.add(postService.PostToPostWithPicture(post));
        }
        System.out.println(name + " 포함한 게시글 목록 반환");
        return postList;
    }

    // 해당 게시글 위치 리턴
    @GetMapping("post/location/{post_num}")
    public Location getPostLocation(@PathVariable int post_num){
        System.out.println(post_num + "번 게시글 판매 위치 반환");
        return postService.getLocation(post_num);
    }

    // 해당 위치에서 해당 반경 내 게시글 목록 리턴
    @GetMapping("post/lonlat/{lon}/{lat}/{distance}")
    public List<PostWithPicture> getAroundLocation(@PathVariable double lon, @PathVariable double lat, @PathVariable double distance){
        System.out.println("(" + lon + ", " + lat + ")" + "에서 " + distance + "km 반경 내 게시글 목록 반환");
        return postService.getAroundLocation(lon, lat, distance);
    }

    // 해당 게시글 판매완료 처리
    @GetMapping("/post/{post_num}/soldout")
    public void setStatusSoldOut(@PathVariable int post_num){
        System.out.println(post_num + "번 게시글 거래 완료");
        postService.setStatusSoldout(post_num);
    }

    // 해당 사용자의 판매완료 게시글 목록 리턴
    @GetMapping("/post/sell/{user_num}")
    public List<PostWithPicture> getSellPost(@PathVariable int user_num){
        System.out.println(user_num + "번 고객 판매 목록 반환");
        return postService.getSellPost(user_num);
    }

    // 해당 승인 대기 게시글 승인
    @GetMapping("/approval/{no}/{model_name}")
    public void approvePost(@PathVariable Integer no, @PathVariable String model_name) {
        System.out.println(no + "번 게시글 승인 완료");
        postService.approvePost(no, model_name);
    }

    // 해당 승인 대기 게시글 거절
    @GetMapping("/approval/{no}/reject")
    public void rejectPost(@PathVariable Integer no) {
        System.out.println(no + "번 게시글 승인 거절");
        postService.rejectPost(no);
    }

    // 전체 승인 대기글 목록 리턴
    @GetMapping("/approval")
    public List<PostWithPicture> getAllWaitingApprovalPosts() throws IOException {
        List<PostWithPicture> postList = new ArrayList<>();
        for (Post post : postService.getAllWaitingApprovalPosts()){
            postList.add(postService.PostToPostWithPicture(post));
        }
        System.out.println("승인 대기 게시글 목록 반환");
        return postList;
    }

    // 해당 승인 대기 게시글 리턴
    @GetMapping("/approval/{no}")
    public PostWithPicture getWaitingApprovalPostByNo(@PathVariable Integer no) throws IOException {
        System.out.println("승인 대기 게시글 방문");
        return postService.getPost(no);
    }

    // 해당 게시글 숨김 처리
    @GetMapping("/post/{post_num}/hide")
    public void hidePost(@PathVariable Integer post_num) {
        System.out.println(post_num + "번 게시글 숨김 처리");
        postService.hidePost(post_num);
    }

    // 해당 숨김 처리된 게시글 공개 처리
    @GetMapping("/post/{post_num}/exposure")
    public void exposureHiddenPost(@PathVariable Integer post_num) {
        System.out.println(post_num + "번 게시글 공개 처리");
        postService.exposureHiddenPost(post_num);
    }

    // 전체 숨김 게시글 목록 리턴
    @GetMapping("/post/hidden")
    public List<PostWithPicture> getHiddenPosts() throws IOException {
        System.out.println("숨김 게시글 목록 반환");
        return postService.getHiddenPosts();
    }
}