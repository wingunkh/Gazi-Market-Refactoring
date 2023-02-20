package capstone.capstone.service;

import capstone.capstone.domain.Posts;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {
    @Autowired
    private PostRepository postRepository;

    // get boards data
    public List<Posts> getAllPost() {
        return postRepository.findAll();
    }

    // create board
    public Posts createPost(Posts post) {
        return postRepository.save(post);
    }




}
