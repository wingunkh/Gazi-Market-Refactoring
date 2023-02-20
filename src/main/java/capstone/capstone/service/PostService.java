package capstone.capstone.service;

import capstone.capstone.domain.Posts;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PostService {
    @Autowired
    private PostRepository postRepository;

    //Repository를 호출해서 글목록 데이터를 리턴하는 메소드.
    // get boards data
    public List<Posts> getAllPost() {
        return postRepository.findAll();
    }

    // create board
    public Posts createPost(Posts post) {
        return postRepository.save(post);
    }


    // get post one by id
    public ResponseEntity<Posts> getPost(Integer no) {
        Posts post = postRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(post);
    }




}
