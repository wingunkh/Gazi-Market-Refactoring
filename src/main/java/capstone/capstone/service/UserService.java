package capstone.capstone.service;

import capstone.capstone.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMemberRepository usermemberRepository;

    public String findName(int cht_member){
        return  usermemberRepository.findName(cht_member);
    }
}
