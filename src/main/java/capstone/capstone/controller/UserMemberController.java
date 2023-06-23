package capstone.capstone.controller;

import capstone.capstone.domain.User_Member;
import capstone.capstone.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class UserMemberController {
    @Autowired
    private UserMemberService userMemberService;

    // 해당 사용자 프로필 이미지 리턴
    @GetMapping("/user/{user_num}")
    public String showProfileImage(@PathVariable Integer user_num) {
        System.out.println(user_num + "번 고객 프로필 이미지 반환");
        return userMemberService.showProfileImage(user_num);
    }

    // 해당 사용자 프로필 이미지 수정
    @PostMapping("/user/update")
    public void updateProfileImage(
            @RequestPart(value = "user_num")
            Integer user_num,
            @RequestPart(value = "file")
            List<MultipartFile> file
    ) throws Exception {
        System.out.println(user_num + "번 고객 프로필 이미지 수정");
        userMemberService.updateProfileImage(user_num, file);
    }

    // 모든 사용자 정보 리턴
    @GetMapping("/user/info")
    public List<User_Member> getAllUserInfo(){
        System.out.println("전체 고객 정보 반환");
        return userMemberService.getAllUserInfo();
    }

    // 해당 사용자 정보 리턴
    @GetMapping("/user/info/{user_num}")
    public User_Member getUserInfo(@PathVariable Integer user_num){
        System.out.println(user_num + "번 고객 정보 반환");
        return userMemberService.getUserInfo(user_num);
    }
}