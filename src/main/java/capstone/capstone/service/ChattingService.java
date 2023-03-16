package capstone.capstone.service;

import capstone.capstone.domain.Categories;
import capstone.capstone.domain.Chatting;
import capstone.capstone.repository.CategoryRepository;
import capstone.capstone.repository.ChattingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChattingService {
    @Autowired
    private ChattingRepository chattingRepository;

    public List<Chatting> getAllChattingDate(int cht_room_no) {
        return chattingRepository.findAllDate(cht_room_no);
    }


    public String getLastmsg(int cht_room_no){
        return  chattingRepository.findlastmsg(cht_room_no);
    }
}
