package capstone.capstone.service;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.Picture;
import capstone.capstone.repository.ChattingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChattingService {
    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private UserService userService;

    public List<Chatting> getAllChattingDate(int cht_room_num) {
        return chattingRepository.findAllDate(cht_room_num);
    }


    public String getLastmsg(int cht_room_num){
        return  chattingRepository.findlastmsg(cht_room_num);
    }

    public Chatting createChatting(Chatting chatting) {
        return chattingRepository.save(chatting);
    }

}