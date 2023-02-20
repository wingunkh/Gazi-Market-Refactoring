package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositiory extends JpaRepository<Posts, JpaRepository> {

}
