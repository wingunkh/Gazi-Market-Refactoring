package capstone.capstone.controller;

import capstone.capstone.domain.Posts;
import capstone.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    // get all board
    @GetMapping("/post")
    public List<Posts> getAllPosts() {
        return postService.getAllPost();
    }

    // create board
    @PostMapping("/post")
    public Posts createPost(@RequestBody Posts post) {
        return postService.createPost(post);
    }




}
