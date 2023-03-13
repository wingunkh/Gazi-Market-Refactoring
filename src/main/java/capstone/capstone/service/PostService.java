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

    public List<Posts> getAllPost() {
        System.out.println("get All posts");
        System.out.println(postRepository.findAll());
        return postRepository.findAll();
    }

    public List<Posts> getDatePost() {
        return postRepository.findDate();
    }

    public Posts createPost(Posts post) {
        return postRepository.save(post);
    }

    public ResponseEntity<Posts> getPost(Integer no) {
        Posts post = postRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(post);
    }

    public List<Posts> getcategoryPosts(String category){
        return postRepository.findCategory(category);
    }
}