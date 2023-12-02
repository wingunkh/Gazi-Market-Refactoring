package capstone.capstone.service;

import capstone.capstone.domain.LikeList;
import capstone.capstone.dto.PostResponse;
import capstone.capstone.domain.VisitList;
import capstone.capstone.repository.LikeListRepository;
import capstone.capstone.repository.VisitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {
    @Autowired
    private PostService postService;

    @Autowired
    private LikeListRepository likelistRepository;

    @Autowired
    private VisitListRepository visitListRepository;

    public void addLikeList(Integer user_num, Integer post_num) {
        LikeList like = new LikeList(user_num, post_num);

        likelistRepository.save(like);
    }

    public void deleteLikeList(Integer user_num, Integer post_num) {
        LikeList like = new LikeList(user_num, post_num);

        likelistRepository.delete(like);
    }

    public List<PostResponse> getLikeList(Integer user_num) throws IOException {
        List<Integer> postlist = likelistRepository.getLikeList(user_num);
        List<PostResponse> postWithPictureslist = new ArrayList<>();

        for (Integer n : postlist) {
            PostResponse postResponse = postService.findById(n);
            if (postResponse != null) {
                postWithPictureslist.add(postResponse);
            }
        }

        return postWithPictureslist;
    }

    public List<Integer> getLikeListPostNum(Integer user_num) throws IOException {
        return likelistRepository.getLikeList(user_num);
    }

    public void visit(Integer postNum, Integer userNum) {
        VisitList visit = new VisitList(postNum, userNum);

        visitListRepository.save(visit);
    }

    public void deleteVisitList(Integer user_num, Integer post_num) {
        VisitList visit = new VisitList(user_num, post_num);

        visitListRepository.delete(visit);
    }

    public List<PostResponse> getVisitList(Integer user_num) throws IOException {
        List<Integer> postlist = visitListRepository.getVisitList(user_num);
        List<PostResponse> postWithPictureslist = new ArrayList<>();

        for (Integer n : postlist) {
            PostResponse postResponse = postService.findById(n);
            if (postResponse != null) {
                postWithPictureslist.add(postResponse);
            }
        }

        return postWithPictureslist;
    }
}