package capstone.capstone.repository;

import capstone.capstone.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserMemberRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT u.nickname FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    String getNickName(@Param("user_num") int user_num);

    @Query(value = "SELECT u.profile_image FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    String showProfileImage(@Param("user_num") int user_num);

    @Query(value = "SELECT * FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    Users getUserInfo(@Param("user_num") int user_num);

    @Query(value = "SELECT * FROM User_Member ORDER BY user_num", nativeQuery = true)
    List<Users> getAllUserInfo();

    @Query(value = "UPDATE u.profile_image SET :profile_image FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    void updateProfileImage(@Param("user_num") int user_num, String profile_image);
}