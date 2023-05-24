package capstone.capstone.service;


import capstone.capstone.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class UserMemberService {
    @Autowired
    private UserMemberRepository userMemberRepository;

    @Autowired
    private FileHandler fileHandler;

    public String showProfileImage(int user_num) {
        if(userMemberRepository.showProfileImage(user_num).isEmpty()){
            return "https://capstone-eggplant-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.png";
        }else {
            return userMemberRepository.showProfileImage(user_num);
        }
    }

    public void updateProfileImage(int user_num, List<MultipartFile> file) throws Exception {
        // Amazon S3에 전달받은 사진을 업로드하고 해당 사진의 Url이 담긴 Url 리스트를 반환받아 변수 list에 저장
        List<String> list = fileHandler.saveToS3(file, "profile/");
        userMemberRepository.updateProfileImage(user_num, list.get(0));
    }
}
