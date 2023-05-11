package capstone.capstone.service;

import capstone.capstone.domain.Picture;
import capstone.capstone.domain.Post;
import capstone.capstone.domain.PostWithPicture;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.repository.PictureRepository;
import capstone.capstone.repository.PostRepository;
import capstone.capstone.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private ModelService modelService;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserMemberRepository userMemberRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private FileHandler fileHandler;

    public List<PostWithPicture> getAllPosts() throws IOException {
        List<PostWithPicture> allPosts = new ArrayList<PostWithPicture>();

        List<Post> list = postRepository.findAllPosts();
        for(Post post : list) {
            PostWithPicture postWithPicture = new PostWithPicture(post);

            postWithPicture.setNickname(userMemberRepository.getNicknameByUserNum(post.getUser_num()));
            postWithPicture.setCategory_name(modelService.getCategoryName(post.getModel_name()));
            postWithPicture.setPictureURL(pictureRepository.getPictureLocationByPostNo(post.getPost_num()));

            allPosts.add(postWithPicture);
        }

        return allPosts;
    }

    public void createPost(Post post, List<MultipartFile> files) throws Exception {
        postRepository.save(post);

        // Amazon S3에 전달받은 사진을 업로드하고 해당 사진의 정보가 담긴 Picture 리스트를 반환받아 변수 list에 저장
        List<Picture> list = fileHandler.saveToS3(post.getPost_num(), files);
        for(Picture picture : list) {
            pictureRepository.save(picture);
        }
    }

    public void updatePost(Post post) throws Exception {
        postRepository.updatePost(post.getPost_num(), post.getModel_name(), post.getGrade(), post.getStatus(), post.getPrice(), post.getPost_title(), post.getPost_content());
    }

    public void deletePost(Integer num) {
        List<String> list = pictureRepository.getPictureLocationByPostNo(num);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        postRepository.deletePost(num);
    }

    public void approvePost(Integer num, String model_name) {
        postRepository.approvePost(num, model_name);
    }

    public void rejectPost(Integer num) {
        List<String> list = pictureRepository.getPictureLocationByPostNo(num);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        postRepository.rejectPost(num);
    }

    public List<Post> getAllWaitingApprovalPosts() throws IOException {
        return postRepository.findAllWaitingApprovalPosts();
    }

    public PostWithPicture getPost(Integer num) throws IOException {
        PostWithPicture postWithPicture = new PostWithPicture(postRepository.findById(num)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by no : ["+num+"]")));

        postWithPicture.setNickname(userMemberRepository.getNicknameByUserNum(postWithPicture.getUser_num()));
        postWithPicture.setCategory_name(modelService.getCategoryName(postWithPicture.getModel_name()));
        postWithPicture.setPictureURL(pictureRepository.getPictureLocationByPostNo(num));

        return postWithPicture;
    }

    public List<Post> getCategoryPosts(String category){
        return postRepository.findCategory(category);
    }

    public List<Post> getModelPosts(String model) {
        return postRepository.findModel(model);
    }

    public List<Post> getNamePosts(String type, String name) {
        return postRepository.findIncludeName(type, name);
    }

    public String getPost_Name(int post_num) { return postRepository.findName(post_num); }

    public String getPost_Host_info(int post_num) { return postRepository.findHostInfo(post_num);}
}