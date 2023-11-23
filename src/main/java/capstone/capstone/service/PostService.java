package capstone.capstone.service;

import capstone.capstone.domain.*;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.dto.PostResponse;
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
    private MemberService memberService;

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private ImageSourceHandler imageSourceHandler;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private VisitListRepository visitListRepository;

    @Autowired
    private LikeListRepository likeListRepository;

    public PostResponse convertPostToPostResponse(Post post) {
        String categoryName = post.getModel().getCategory().getCategoryName();
        String pictureUrl = pictureRepository.findByPostPostNum(post.getPostNum()).getLocation();
        Double marketPrice = calculateMarketPrice(post.getModel().getModelName(), post.getGrade());
        Location location = new Location(postRepository.getLa(post.getUserNum()), postRepository.getLo(post.getUserNum()));
        String profileImage;
        if (memberService.getProfileImage(post.getUserNum()) == null) {
            profileImage = "https://gazi-market-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.jpg";
        } else {
            profileImage = memberService.getProfileImage(post.getUserNum());
        }
        String nickName = memberService.findById(post.getUserNum()).getNickname();

        return new PostResponse(post, categoryName, pictureUrl, marketPrice, location, profileImage, nickName);
    }

    public void createPost(Post post, MultipartFile file) throws Exception {
        // 해당 이미지가 직접 촬영한 이미지인지 도용한 이미지인지 확인
        String imageSource = imageSourceHandler.detectImageSource(file);

        if(imageSource == "CAPTURED") {
            System.out.println("해당 이미지는 직접 촬영한 이미지로 추정됩니다.");
            post.setImageSource(1);
        } else if(imageSource == "DOWNLOADED") {
            System.out.println("해당 이미지는 인터넷을 통해 다운로드된 이미지로 추정됩니다.");
            post.setImageSource(0);
        } else {
            System.out.println("해당 이미지는 직접 촬영한 이미지로 추정됩니다.");
            post.setImageSource(1);
        }

        postRepository.save(post);

        // Amazon S3에 전달받은 사진들을 업로드하고 해당 사진의 Url을 반환받아 변수에 저장
        String pictureUrl = fileHandler.saveToS3(file, "images/");


        // Picture 객체 생성 후 Picture 리스트에 추가
        Picture picture = Picture.builder()
                .post(post)
                .location(pictureUrl)
                .build();

        pictureRepository.save(picture);
    }

    public List<PostResponse> getAllPost() throws IOException {
        List<PostResponse> allPosts = new ArrayList<>();
        List<Post> list = postRepository.getAllPost();

        for(Post post : list) {
            PostResponse postResponse = convertPostToPostResponse(post);
            allPosts.add(postResponse);
        }

        return allPosts;
    }

    public PostResponse getPostByNum(Integer post_num) throws IOException {
        return convertPostToPostResponse(postRepository.findById(post_num)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by no : ["+post_num+"]")));
    }

    public List<PostResponse> getPostByCategory(String category_name) {
        List<PostResponse> Posts = new ArrayList<>();
        List<Post> list = postRepository.getPostByCategory(category_name);

        for(Post post : list) {
            PostResponse postResponse = convertPostToPostResponse(post);
            Posts.add(postResponse);
        }

        return Posts;
    }

    public List<PostResponse> getPostByModel(String model_name) {
        List<PostResponse> Posts = new ArrayList<>();
        List<Post> list = postRepository.getPostByModel(model_name);

        for(Post post : list) {
            PostResponse postResponse = convertPostToPostResponse(post);
            Posts.add(postResponse);
        }

        return Posts;
    }

    public List<PostResponse> getTodayPost() {
        List<Post> list = postRepository.getTodayPost();
        List<PostResponse> postResponses = new ArrayList<>();

        for(Post p: list) {
            postResponses.add(convertPostToPostResponse(p));
        }

        return postResponses;
    }

    public void updatePost(Integer post_num, Post post) throws Exception {
        postRepository.updatePost(post_num, post.getModel().getModelName(), post.getGrade(), post.getStatus(), post.getPrice(), post.getPostTitle(), post.getPostContent());
    }

    public void deletePost(Integer post_num) {
        String pictureUrl = pictureRepository.findByPostPostNum(post_num).getLocation();

        fileHandler.deleteFromS3(pictureUrl);
        visitListRepository.deletePost(post_num);
        likeListRepository.deletePost(post_num);
        postRepository.deletePost(post_num);
    }

    public List<PostResponse> getPostByName(String type, String name) {
        List<Post> list;
        String text = "%" + name + "%";
        list = postRepository.getPostByName(text);
        List<PostResponse> Posts = new ArrayList<>();

        for(Post post : list) {
            PostResponse postResponse = convertPostToPostResponse(post);
            Posts.add(postResponse);
        }

        return Posts;
    }

    public Location getPostLocation(Integer post_num) {
        return new Location(postRepository.getPostLocationLa(post_num),postRepository.getPostLocationLo(post_num));
    }

    public List<PostResponse> getAroundPost(double lon, double lat, double distance) {
        List<Integer> user_id = postRepository.getAroundPost(lon, lat, distance);
        List<Post> postList = new ArrayList<>();
        List<PostResponse> postResponses = new ArrayList<>();

        for(Integer id : user_id) {
            postList.addAll(postRepository.findAllUser(id));
        }

        for(Post p: postList) {
            postResponses.add(convertPostToPostResponse(p));
        }

        return postResponses;
    }

    public void soldOut(Integer post_num) {
        postRepository.soldOut(post_num);
    }

    public List<PostResponse> getSoldOutPost(Integer user_num) {
        List<Post> list = postRepository.getSoldOutPost(user_num);
        List<PostResponse> postResponses = new ArrayList<>();

        for(Post p: list){
            postResponses.add(convertPostToPostResponse(p));
        }

        return postResponses;
    }

    public void approvePost(Integer num, String model_name) {
        postRepository.approvePost(num, model_name);
    }

    public void rejectPost(Integer num) {
        String pictureUrl = pictureRepository.findByPostPostNum(num).getLocation();


        fileHandler.deleteFromS3(pictureUrl);
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

    public List<PostResponse> getHiddenPost() throws IOException {
        List<PostResponse> allPosts = new ArrayList<>();
        List<Post> list = postRepository.getHiddenPost();

        for(Post post : list) {
            PostResponse postResponse = convertPostToPostResponse(post);
            allPosts.add(postResponse);
        }

        return allPosts;
    }

    public String getPostName(Integer post_num) { return postRepository.getPostName(post_num); }

    public String getHostInfo(Integer post_num) { return postRepository.getHostInfo(post_num); }

    public Double calculateMarketPrice(String modelName, String grade) {
        List<Post> postList = postRepository.findByModelModelNameAndGrade(modelName, grade);
        Integer sum = 0;
        
        for (Post post : postList) {
            sum += post.getPrice();
        }
        
        return (double) (sum / postList.size());
    }
}