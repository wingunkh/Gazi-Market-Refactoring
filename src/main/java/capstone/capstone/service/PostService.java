package capstone.capstone.service;

import capstone.capstone.domain.Posts;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<List<Posts>> getAllPost() {
        List<Posts> posts = postRepository.findALLList();
        return ResponseEntity.ok(posts);
    }

/*    public List<Posts> getAllPost() {
        return postRepository.findAll();
    }*/

    public Posts createPost(Posts post) {
        return postRepository.save(post);
    }

    public ResponseEntity<Posts> getPost(Integer no) {
        Posts post = postRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(post);
    }
}