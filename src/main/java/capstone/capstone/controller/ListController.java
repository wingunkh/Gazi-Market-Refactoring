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
        listService.addLikeList(user_num, post_num);
        System.out.println(user_num + "번 사용자 " + post_num + "번 게시글 즐겨찾기 추가");
    }

    // 해당 사용자의 즐겨찾기 목록 리턴
    @GetMapping("/like/{user_num}")
    public List<PostWithPicture> getLikeList(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 사용자의 즐겨찾기 목록 반환");
        return listService.getLikeList(user_num);
    }

    // 해당 사용자의 즐겨찾기 게시글 번호 리턴
    @GetMapping("/like/num/{user_num}")
    public List<Integer> getLikeListPostNum(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 사용자의 즐겨찾기 게시글 번호 반환");
        return listService.getLikeListPostNum(user_num);
    }

    // 해당 사용자의 즐겨찾기 삭제
    @GetMapping("/like/delete/{user_num}/{post_num}")
    public void deleteLikeList(@PathVariable int user_num, @PathVariable int post_num){
        listService.deleteLikeList(user_num, post_num);
        System.out.println(user_num + "번 사용자의 " + post_num + "번 게시글 즐겨찾기 삭제");
    }

    // 해당 사용자의 방문기록 목록 리턴
    @GetMapping("/visit/{user_num}")
    public List<PostWithPicture> getVisitList(@PathVariable int user_num) throws IOException {
        System.out.println(user_num + "번 사용자의 방문기록 목록 반환");
        return listService.getVisitList(user_num);
    }

    // 해당 사용자의 방문기록 삭제
    @GetMapping("/visit/delete/{user_num}/{post_num}")
    public void deleteVisitList(@PathVariable int user_num, @PathVariable int post_num){
        System.out.println(user_num + "번 사용자의 " + post_num + "번 게시글 방문기록 삭제");
        listService.deleteVisitList(user_num, post_num);
    }
}