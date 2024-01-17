package capstone.capstone.service;

import capstone.capstone.domain.*;
import capstone.capstone.dto.Location;
import capstone.capstone.dto.PostResponse;
import capstone.capstone.handler.FileHandler;
import capstone.capstone.handler.ImageSourceHandler;
import capstone.capstone.idclass.History_Post;
import capstone.capstone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final MemberService memberService;

    private final FileHandler fileHandler;

    private final ImageSourceHandler imageSourceHandler;

    private final PostRepository postRepository;

    private final PictureRepository pictureRepository;

    private final VisitHistoryRepository visitHistoryRepository;

    private final LikeHistoryRepository likeHistoryRepository;

    private final MemberRepository memberRepository;

    public PostResponse convertPostToPostResponse(Post post) {
        String categoryName = post.getModel().getCategory().getCategoryName();
        String pictureUrl = pictureRepository.findByPostPostNum(post.getPostNum()).getLocation();
        Double marketPrice = calculateMarketPrice(post.getModel().getModelName(), post.getGrade());
        Member member = memberService.findMemberById(post.getMember().getMemberNum());
        Location location = new Location(member.getLatitude(), member.getLongitude());
        String profileImage = "";

        if (memberService.findProfileImage(post.getMember().getMemberNum()) == null) {
            profileImage = "https://gazi-market-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.jpg";

        } else {
            profileImage = memberService.findProfileImage(post.getMember().getMemberNum());
        }

        String nickName = memberService.findMemberById(post.getMember().getMemberNum()).getNickname();

        return new PostResponse(post, categoryName, pictureUrl, marketPrice, location, profileImage, nickName);
    }

    public Post savePost(Post post, MultipartFile file) throws Exception {
        String imageSource = imageSourceHandler.detectImageSource(file);

        if (Objects.equals(imageSource, "CAPTURED")) {
            post.setImageSource(1);
        } else if (Objects.equals(imageSource, "DOWNLOADED")) {
            post.setImageSource(0);
        } else {
            post.setImageSource(1);
        }

        // Post 객체를 먼저 저장
        post = postRepository.save(post);

        // Amazon S3 Bucket에 전달받은 사진들을 업로드하고 해당 사진의 Url을 반환받아 변수에 저장
        String pictureUrl = fileHandler.saveToS3(file, "images/");

        Picture picture = Picture.builder()
                .post(post)
                .location(pictureUrl)
                .build();

        pictureRepository.save(picture);

        return post;
    }

    public List<PostResponse> findAllPosts() {
        List<Post> postList = postRepository.findAll();
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public PostResponse findPostById(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        return convertPostToPostResponse(optionalPost.get());
    }

    @Transactional
    public String updatePost(Post post) {
        Optional<Post> optionalPost = postRepository.findById(post.getPostNum());

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Post targetPost = optionalPost.get();

        targetPost.setModel(post.getModel());
        targetPost.setPostTitle(post.getPostTitle());
        targetPost.setPostContent(post.getPostContent());
        targetPost.setGrade(post.getGrade());
        targetPost.setPrice(post.getPrice());
        targetPost.setWrittenDate(LocalDateTime.now());

        return ResponseEntity.ok().toString();
    }

    @Transactional
    public String deletePost(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Post targetPost = optionalPost.get();

        String pictureUrl = pictureRepository.findByPostPostNum(targetPost.getPostNum()).getLocation();
        pictureRepository.deleteById(pictureUrl);
        fileHandler.deleteFromS3(pictureUrl);

        History_Post listPost = new History_Post(targetPost.getPostNum(), targetPost.getMember().getMemberNum());

        if (visitHistoryRepository.existsById(listPost)) {
            visitHistoryRepository.deleteById(listPost);
        }

        if (likeHistoryRepository.existsById(listPost)) {
            likeHistoryRepository.deleteById(listPost);
        }

        postRepository.delete(targetPost);

        return ResponseEntity.ok().toString();
    }

    public List<PostResponse> findAllTodayPosts() {
        List<Post> postList = postRepository.findAllByWrittenDateBetween(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN), LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX));
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post: postList) {
            postResponseList.add(convertPostToPostResponse(post));
        }

        return postResponseList;
    }

    public List<PostResponse> findAllPostsByModelName(String modelName) {
        List<Post> postList = postRepository.findAllByModelModelName(modelName);
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public List<PostResponse> searchPostByKeyword(String keyWord) {
        List<Post> postList = postRepository.findAllByPostTitleContainingOrPostContentContainingOrderByWrittenDateDesc(keyWord, keyWord);
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public List<PostResponse> findAllNearbyPosts(Double latitude, Double longitude, Double distance) {
        Double earthRadius = 6371.0;
        Double latitudeAngularDistance = (distance / earthRadius) * (180.0 / Math.PI);
        Double longitudeAngularDistance = (distance / earthRadius) * (180.0 / Math.PI) / Math.cos(latitude * Math.PI / 180);

        List<Member> memberList = memberRepository.findAllByLatitudeBetweenAndLongitudeBetween(
                latitude - latitudeAngularDistance,
                latitude + latitudeAngularDistance,
                longitude - longitudeAngularDistance,
                longitude + longitudeAngularDistance
        );

        List<Post> postList = new ArrayList<>();
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Member member : memberList) {
            postList.addAll(postRepository.findAllByMemberMemberNum(member.getMemberNum()));
        }

        for (Post post: postList) {
            postResponseList.add(convertPostToPostResponse(post));
        }

        return postResponseList;
    }

    public Location findPostLocation(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Member member = memberService.findMemberById(optionalPost.get().getMember().getMemberNum());

        return new Location(member.getLatitude(), member.getLongitude());
    }

    public List<PostResponse> findAllSoldOutPosts(Integer memberNum) {
        List<Post> postList = postRepository.findAllByMemberMemberNumAndStatusOrderByWrittenDate(memberNum, "판매 완료");
        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post: postList) {
            postResponses.add(convertPostToPostResponse(post));
        }

        return postResponses;
    }

    @Transactional
    public String soldOut(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Post targetPost = optionalPost.get();

        targetPost.setStatus("판매 완료");

        return ResponseEntity.ok().toString();
    }

    public Double calculateMarketPrice(String modelName, String grade) {
        List<Post> postList = postRepository.findAllByModelModelNameAndGrade(modelName, grade);
        Integer sum = 0;
        
        for (Post post : postList) {
            sum += post.getPrice();
        }
        
        return (double) (sum / postList.size());
    }
}