package capstone.capstone.repository;

import capstone.capstone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByWrittenDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Post> findAllByModelModelName(String modelName);

    List<Post> findAllByPostTitleContainingOrPostContentContainingOrderByWrittenDateDesc(String postTitle, String postContent);

    List<Post> findAllByMemberMemberNum(Integer memberNum);

    List<Post> findAllByMemberMemberNumAndStatusOrderByWrittenDate(Integer memberNum, String status);

    List<Post> findAllByModelModelNameAndGrade(String modelName, String grade);
}