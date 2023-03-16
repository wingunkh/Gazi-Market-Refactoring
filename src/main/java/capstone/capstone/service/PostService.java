package capstone.capstone.service;

import capstone.capstone.domain.Picture;
import capstone.capstone.domain.Posts;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.repository.PictureRepository;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private FileHandler fileHandler;

    public Posts createPost(Posts post, List<MultipartFile> files) throws Exception {
        List<Picture> list = fileHandler.parseFileInfo(post.getPost_no(), files);

        List<Picture> pictures = new ArrayList<>();
        for(Picture picture : list) {
            pictures.add(pictureRepository.save(picture));
        }

        return postRepository.save(post);
    }

    public List<Posts> getAllPost() {
        System.out.println("get All posts");
        System.out.println(postRepository.findAll());
        return postRepository.findAll();
    }

    public ResponseEntity<Posts> getPost(Integer no) {
        Posts post = postRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(post);
    }

    public List<Posts> getDatePost() {
        return postRepository.findDate();
    }

    public List<Posts> getCategoryPosts(String category){
        return postRepository.findCategory(category);
    }

    public List<Posts> getModelPosts(String model) { return postRepository.findModel(model); }

    public String getPost_Name(int post_no) { return postRepository.findName(post_no); }

    public String getPost_Host_info(int post_no) { return postRepository.findHostInfo(post_no);}
}