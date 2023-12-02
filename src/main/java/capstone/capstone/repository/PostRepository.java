package capstone.capstone.repository;

import capstone.capstone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByWrittenDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Post> findAllByModelModelName(String modelName);

    List<Post> findByPostTitleContainingOrPostContentContainingOrderByWrittenDateDesc(String postTitle, String postContent);

    List<Post> findAllByMemberMemberNum(Integer memberNum);

    List<Post> findAllByMemberMemberNumAndStatusOrderByWrittenDate(Integer memberNum, String status);

    List<Post> findByModelModelNameAndGrade(@Param("modelName") String modelName, @Param("grade") String grade);
}