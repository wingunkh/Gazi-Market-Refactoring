package capstone.capstone.service;

import capstone.capstone.domain.*;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.extendedDomain.PostWithPicture;
import capstone.capstone.repository.*;
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

    @Autowired
    private ImageSourceHandler imageSourceHandler;

    @Autowired
    private VisitListRepository visitListRepository;

    @Autowired
    private LikeListRepository likeListRepository;

    public PostWithPicture PostToPostWithPicture(Post post){
        PostWithPicture postWithPicture = new PostWithPicture(post);

        postWithPicture.setNickname(userMemberRepository.getNickname(post.getUser_num()));
        postWithPicture.setCategory_name(modelService.getCategoryName(post.getModel_name()));
        postWithPicture.setPictureURL(pictureRepository.getPictureLocation(post.getPost_num()));
        postWithPicture.setFairPrice(postRepository.getMarketPrice(post.getModel_name(), post.getGrade()));
        postWithPicture.setLocation(postRepository.getLa(post.getUser_num()), postRepository.getLo(post.getUser_num()));

        if(userMemberRepository.showProfileImage(post.getUser_num()) == null) {
            postWithPicture.setProfile_image("https://capstone-eggplant-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.jpg");
        } else {
            postWithPicture.setProfile_image(userMemberRepository.showProfileImage(post.getUser_num()));
        }

        return postWithPicture;
    }

    public void createPost(Post post, List<MultipartFile> files) throws Exception {
        // 해당 이미지가 직접 촬영한 이미지인지 도용한 이미지인지 확인
        String imageSource = imageSourceHandler.detectImageSource(files.get(0));

        if(imageSource == "CAPTURED") {
            System.out.println("해당 이미지는 직접 촬영한 이미지로 추정됩니다.");
            post.setIsCaptured(1);
        } else if(imageSource == "DOWNLOADED") {
            System.out.println("해당 이미지는 인터넷을 통해 다운로드된 이미지로 추정됩니다.");
            post.setIsCaptured(0);
        }
        else {
            System.out.println("해당 이미지는 직접 촬영한 이미지로 추정됩니다.");
            post.setIsCaptured(1);
        }

        postRepository.save(post);

        // Amazon S3에 전달받은 사진들을 업로드하고 해당 사진들의 Url이 담긴 Url 리스트를 반환받아 변수 list에 저장
        List<String> list = fileHandler.saveToS3(files, "images/");

        for(String imageUrl : list) {
            // Picture 객체 생성 후 Picture 리스트에 추가
            Picture picture = Picture.builder()
                    .post_num(post.getPost_num())
                    .picture_location(imageUrl).build();

            pictureRepository.save(picture);
        }
    }

    public List<PostWithPicture> getAllPost() throws IOException {
        List<PostWithPicture> allPosts = new ArrayList<PostWithPicture>();

        List<Post> list = postRepository.getAllPost();
        for(Post post : list) {
            PostWithPicture postWithPicture = PostToPostWithPicture(post);
            allPosts.add(postWithPicture);
        }

        return allPosts;
    }

    public PostWithPicture getPostByNum(Integer post_num) throws IOException {
        PostWithPicture postWithPicture = PostToPostWithPicture(postRepository.findById(post_num)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by no : ["+post_num+"]")));

        return postWithPicture;
    }

    public List<PostWithPicture> getPostByCategory(String category){
        List<PostWithPicture> Posts = new ArrayList<PostWithPicture>();

        List<Post> list = postRepository.getPostByCategory(category);
        for(Post post : list) {
            PostWithPicture postWithPicture = PostToPostWithPicture(post);
            Posts.add(postWithPicture);
        }

        return Posts;
    }

    public List<PostWithPicture> getPostByModel(String model) {
        List<PostWithPicture> Posts = new ArrayList<PostWithPicture>();

        List<Post> list = postRepository.getPostByModel(model);
        for(Post post : list) {
            PostWithPicture postWithPicture = PostToPostWithPicture(post);
            Posts.add(postWithPicture);
        }

        return Posts;
    }

    public List<PostWithPicture> getTodayPost(){
        List<Post> list = postRepository.getTodayPost();

        List<PostWithPicture> postWithPictures = new ArrayList<>();
        for(Post p: list){
            postWithPictures.add(PostToPostWithPicture(p));
        }

        return postWithPictures;
    }

    public void updatePost(Integer post_num, Post post) throws Exception {
        postRepository.updatePost(post_num, post.getModel_name(), post.getGrade(), post.getStatus(), post.getPrice(), post.getPost_title(), post.getPost_content());
    }

    public void deletePost(Integer num) {
        List<String> list = pictureRepository.getPictureLocation(num);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        visitListRepository.deletePost(num);
        likeListRepository.deletePost(num);
        postRepository.deletePost(num);
    }

    public List<PostWithPicture> getPostByName(String type, String name) {
        List<Post> list;
        String text = "%" + name + "%";
        list = postRepository.getPostByName(text);

        List<PostWithPicture> Posts = new ArrayList<PostWithPicture>();
        for(Post post : list) {
            PostWithPicture postWithPicture = PostToPostWithPicture(post);
            Posts.add(postWithPicture);
        }

        return Posts;
    }

    public Location getPostLocation(int post_num) {
        return new Location(postRepository.getPostLocationLa(post_num),postRepository.getPostLocationLo(post_num));
    }

    public List<PostWithPicture> getAroundPost(double lon, double lat, double distance){
        List<Integer> user_id = postRepository.getAroundPost(lon, lat, distance);
        List<Post> postList = new ArrayList<>();
        List<PostWithPicture> postWithPictures = new ArrayList<>();

        for(int id : user_id){
            postList.addAll(postRepository.findAllUser(id));
        }

        for(Post p: postList){
            postWithPictures.add(PostToPostWithPicture(p));
        }

        return postWithPictures;
    }

    public void soldOut(int post_num){
        postRepository.soldOut(post_num);
    }

    public List<PostWithPicture> getSoldOutPost(int user_num){
        List<Post> list = postRepository.getSoldOutPost(user_num);

        List<PostWithPicture> postWithPictures = new ArrayList<>();
        for(Post p: list){
            postWithPictures.add(PostToPostWithPicture(p));
        }

        return postWithPictures;
    }

    public void approvePost(Integer num, String model_name) {
        postRepository.approvePost(num, model_name);
    }

    public void rejectPost(Integer num) {
        List<String> list = pictureRepository.getPictureLocation(num);
        for(String picture_location : list) {
            fileHandler.deleteFromS3(picture_location);
        }

        postRepository.rejectPost(num);
    }

    public List<Post> getAllWaitingApprovalPost() throws IOException {
        return postRepository.getAllWaitingApprovalPost();
    }

    public void hidePost(Integer post_num) {
        postRepository.hidePost(post_num);
    }

    public void exposureHiddenPost(Integer post_num) {
        postRepository.exposureHiddenPost(post_num);
    }

    public List<PostWithPicture> getHiddenPost() throws IOException {
        List<PostWithPicture> allPosts = new ArrayList<PostWithPicture>();

        List<Post> list = postRepository.getHiddenPost();
        for(Post post : list) {
            PostWithPicture postWithPicture = PostToPostWithPicture(post);
            allPosts.add(postWithPicture);
        }

        return allPosts;
    }

    public String getPostName(int post_num) { return postRepository.getPostName(post_num); }

    public String getHostInfo(int post_num) { return postRepository.getHostInfo(post_num);}
}