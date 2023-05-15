package capstone.capstone.controller;


import capstone.capstone.domain.PostWithPicture;
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

    @GetMapping("/like/add/{user_num}/{post_num}")
    public void addlikelist(@PathVariable int user_num, @PathVariable int post_num){
        listService.addlike(user_num, post_num);
    }

    @GetMapping("/like/delete/{user_num}/{post_num}")
    public void deleteikelist(@PathVariable int user_num, @PathVariable int post_num){
        listService.deletelike(user_num, post_num);
    }

    @GetMapping("/like/{user_num}")
    public List<PostWithPicture> LikeList(@PathVariable int user_num) throws IOException {
        return listService.likelist(user_num);
    }

    @GetMapping("/like/num/{user_num}")
    public List<Integer> LikeListNum(@PathVariable int user_num) throws IOException {
        return listService.likelistnum(user_num);
    }

    @GetMapping("/visit/delete/{user_num}/{post_num}")
    public void deletevisitlist(@PathVariable int user_num, @PathVariable int post_num){
        listService.deletevisit(user_num, post_num);
    }

    @GetMapping("/visit/{user_num}")
    public List<PostWithPicture> Visitlist(@PathVariable int user_num) throws IOException {
        return listService.visitlist(user_num);
    }
}
