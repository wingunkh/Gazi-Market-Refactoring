package capstone.capstone.repository;

import capstone.capstone.domain.User_member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMemberRepository extends JpaRepository<User_member, Integer> {
    @Query(value="select u.nickname from User_member u where u.user_num = :user_num", nativeQuery = true)
    String getNicknameByUserNum(@Param("user_num") int user_num);
}
