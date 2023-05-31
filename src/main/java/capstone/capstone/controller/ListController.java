package capstone.capstone.controller;

import capstone.capstone.extendedDomain.PostWithPicture;
import capstone.capstone.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class ListController {
    @Autowired
    private ListService listService;

    // 즐겨찾기 추가
    @GetMapping("/like/add/{user_num}/{post_num}")
    public void addLikeList(@PathVariable int user_num, @PathVariable int post_num){
        listService.addlike(user_num, post_num);
        System.out.println(user_num + "번 고객 " + post_num + "번 게시글 즐겨찾기 추가");
    }

    // 즐겨찾기 제거
    @GetMapping("/like/delete/{user_num}/{post_num}")
    public void deleteLikeList(@PathVariable int user_num, @PathVariable int post_num){
        listService.deletelike(user_num, post_num);
        System.out.println(user_num + "번 고객 " + post_num + "번 게시글 즐겨찾기 제거");
    }

    // 즐겨찾기 목록 리턴
    @GetMapping("/like/{user_num}")
    public List<PostWithPicture> likeList(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 고객의 즐겨찾기 목록 반환");
        return listService.likelist(user_num);
    }

    // 즐겨찾기 게시글 번호 리턴
    @GetMapping("/like/num/{user_num}")
    public List<Integer> likeListNum(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 고객 즐겨찾기 게시글 번호 반환");
        return listService.likelistnum(user_num);
    }

    // 방문기록 목록 리턴
    @GetMapping("/visit/{user_num}")
    public List<PostWithPicture> visitList(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 고객 방문 게시글 목록 반환");
        return listService.visitlist(user_num);
    }

    // 방문기록 제거
    @GetMapping("/visit/delete/{user_num}/{post_num}")
    public void deleteVisitList(@PathVariable int user_num, @PathVariable int post_num){
        System.out.println(user_num + "번 고객 " + post_num + "방문 기록 제거");
        listService.deletevisit(user_num, post_num);
    }
}