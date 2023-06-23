package capstone.capstone.service;

import capstone.capstone.domain.Like_list;
import capstone.capstone.extendedDomain.PostWithPicture;
import capstone.capstone.domain.Visit_list;
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

    public void addlike(int user_num, int post_num) {
        Like_list like = new Like_list();

        like.setUser_num(user_num);
        like.setPost_num(post_num);

        likelistRepository.save(like);
    }

    public void deletelike(int user_num, int post_num) {
        Like_list like = new Like_list();

        like.setUser_num(user_num);
        like.setPost_num(post_num);

        likelistRepository.delete(like);
    }

    public void addvisit(int user_num, int post_num) {
        Visit_list visit = new Visit_list();

        visit.setUser_num(user_num);
        visit.setPost_num(post_num);

        visitListRepository.save(visit);
    }

    public void deletevisit(int user_num, int post_num) {
        Visit_list visit = new Visit_list();

        visit.setUser_num(user_num);
        visit.setPost_num(post_num);

        visitListRepository.delete(visit);
    }

    public List<PostWithPicture> getLikeList(int user_num) throws IOException {
        List<Integer> postlist = likelistRepository.findbyuser(user_num);
        List<PostWithPicture> postWithPictureslist = new ArrayList<PostWithPicture>();

        for (int n : postlist) {
            PostWithPicture postWithPicture = postService.getPostByNum(n);
            if (postWithPicture != null) {
                postWithPictureslist.add(postWithPicture);
            }
        }

        return postWithPictureslist;
    }

    public List<Integer> getLikeListNum(int user_num) throws IOException {
        return likelistRepository.findbyuser(user_num);
    }

    public List<PostWithPicture> getVisitList(int user_num) throws IOException {
        List<Integer> postlist = visitListRepository.findbyuser(user_num);
        List<PostWithPicture> postWithPictureslist = new ArrayList<>();

        for (int n : postlist) {
            PostWithPicture postWithPicture = postService.getPostByNum(n);
            if (postWithPicture != null) {
                postWithPictureslist.add(postWithPicture);
            }
        }

        return postWithPictureslist;
    }
}