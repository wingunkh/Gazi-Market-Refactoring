package capstone.capstone.service;

import capstone.capstone.domain.*;
import capstone.capstone.dto.PostResponse;
import capstone.capstone.idclass.History_Post;
import capstone.capstone.repository.*;
import lombok.RequiredArgsConstructor;
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
        Member member = memberService.findById(post.getMember().getMemberNum());
        Location location = new Location(member.getLatitude(), member.getLongitude());
        String profileImage = "";

        if (memberService.getProfileImage(post.getMember().getMemberNum()) == null)
            profileImage = "https://gazi-market-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.jpg";
        else
            profileImage = memberService.getProfileImage(post.getMember().getMemberNum());

        String nickName = memberService.findById(post.getMember().getMemberNum()).getNickname();

        return new PostResponse(post, categoryName, pictureUrl, marketPrice, location, profileImage, nickName);
    }

    public Post save(Post post, MultipartFile file) throws Exception {
        String imageSource = imageSourceHandler.detectImageSource(file);

        if (Objects.equals(imageSource, "CAPTURED"))
            post.setImageSource(1);
        else if (Objects.equals(imageSource, "DOWNLOADED"))
            post.setImageSource(0);
        else
            post.setImageSource(1);

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

    public List<PostResponse> findAll() {
        List<Post> postList = postRepository.findAll();
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public PostResponse findById(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            return convertPostToPostResponse(post);
        } else
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }

    @Transactional
    public String update(Post post) {
        Optional<Post> optionalPost = postRepository.findById(post.getPostNum());

        if (optionalPost.isPresent()) {
            Post targetPost = optionalPost.get();

            targetPost.setModel(post.getModel());
            targetPost.setPostTitle(post.getPostTitle());
            targetPost.setPostContent(post.getPostContent());
            targetPost.setGrade(post.getGrade());
            targetPost.setPrice(post.getPrice());
            targetPost.setWrittenDate(LocalDateTime.now());

            return "게시글 수정 완료";
        } else
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }

    @Transactional
    public String delete(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isPresent()) {
            Post targetPost = optionalPost.get();

            String pictureUrl = pictureRepository.findByPostPostNum(targetPost.getPostNum()).getLocation();
            pictureRepository.deleteById(pictureUrl);
            fileHandler.deleteFromS3(pictureUrl);

            History_Post listPost = new History_Post(targetPost.getPostNum(), targetPost.getMember().getMemberNum());

            if (visitHistoryRepository.existsById(listPost))
                visitHistoryRepository.deleteById(listPost);
            if (likeHistoryRepository.existsById(listPost))
                likeHistoryRepository.deleteById(listPost);

            postRepository.delete(targetPost);

            return "게시글 삭제 완료";
        } else
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }

    public List<PostResponse> findAllByWrittenDate() {
        List<Post> postList = postRepository.findAllByWrittenDateBetween(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN), LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX));
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post: postList) {
            postResponseList.add(convertPostToPostResponse(post));
        }

        return postResponseList;
    }

    public List<PostResponse> findAllByModelModelName(String modelName) {
        List<Post> postList = postRepository.findAllByModelModelName(modelName);
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public List<PostResponse> searchByKeyword(String keyWord) {
        List<Post> postList = postRepository.findByPostTitleContainingOrPostContentContainingOrderByWrittenDateDesc(keyWord, keyWord);
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponse postResponse = convertPostToPostResponse(post);
            postResponseList.add(postResponse);
        }

        return postResponseList;
    }

    public List<PostResponse> findNearby(Double latitude, Double longitude, Double distance) {
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

    public Location findLocation(Integer postNum) {
        Optional<Post> optionalPost = postRepository.findById(postNum);

        if (optionalPost.isPresent()) {
            Member member = memberService.findById(optionalPost.get().getMember().getMemberNum());

            return new Location(member.getLatitude(), member.getLongitude());
        } else
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }

    public List<PostResponse> findSoldOut(Integer memberNum) {
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

        if (optionalPost.isPresent()) {
            Post targetPost = optionalPost.get();

            targetPost.setStatus("판매 완료");

            return "판매 완료 처리 완료";
        } else
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }

    public Double calculateMarketPrice(String modelName, String grade) {
        List<Post> postList = postRepository.findByModelModelNameAndGrade(modelName, grade);
        Integer sum = 0;
        
        for (Post post : postList) {
            sum += post.getPrice();
        }
        
        return (double) (sum / postList.size());
    }
}