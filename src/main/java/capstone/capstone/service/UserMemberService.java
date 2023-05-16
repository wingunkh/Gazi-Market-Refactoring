package capstone.capstone.service;

import capstone.capstone.domain.Picture;
import capstone.capstone.domain.Post;
import capstone.capstone.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserMemberService {
    @Autowired
    private UserMemberRepository userMemberRepository;

    @Autowired
    private FileHandler fileHandler;

    public String showProfileImage(Integer user_num) {
        // userMemberRepository.showProfileImage(user_num);
        return null;
    }

    public void updateProfileImage(Integer user_num, List<MultipartFile> file) throws Exception {
        // userMemberRepository.updateProfileImage();
    }
}
