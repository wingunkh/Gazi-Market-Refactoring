package capstone.capstone.repository;

import capstone.capstone.domain.User_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMemberRepository extends JpaRepository<User_Member, Integer> {
    @Query(value="select u.nickname from User_Member u where u.user_num = :user_num", nativeQuery = true)
    String getNicknameByUserNum(@Param("user_num") int user_num);

    @Query(value="UPDATE u.profile_image SET :profile_image from User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    Void updateProfileImage(@Param("user_num") int user_num, String profile_image);
}
