package capstone.capstone.controller;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Posts;
import capstone.capstone.service.ChattingRoomService;
import capstone.capstone.service.ChattingService;
import capstone.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class ChattingController {
    @Autowired
    private ChattingService chattingService;
    @Autowired
    private ChattingRoomService chattingRoomService;

    @Autowired
    private PostService postService;

    @GetMapping("/chattingroom/{guest_no}")
    public List<ChattingRoomList> getAllChattingRoom(@PathVariable Integer guest_no) {
        List<ChattingRoomList> chattingRoomList = null;
        for( ChattingRoom chattingRoom : chattingRoomService.getAllChattingRoom(guest_no) )
        {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        return chattingRoomList;
    }


    @GetMapping("/chattingroom/{post_no}/{guest_no}")
    public List<Chatting> getChattingByNo(@PathVariable Integer post_no, @PathVariable Integer guest_no) {
        return chattingRoomService.getChattingRoom(post_no, guest_no);
    }

    @GetMapping("/chatting/{cht_room_no}")
    public List<Chatting> getAllChattingDate(@PathVariable Integer cht_room_no){
        return chattingService.getAllChattingDate(cht_room_no);
    }


    public class ChattingRoomList{
        int cht_room_no;
        int post_num;
        String host_info;
        String post_name;
        String last_cht_msg;

        public ChattingRoomList(ChattingRoom chattingRoom) {
            this.cht_room_no = chattingRoom.getCht_room_num();
            this.post_num = chattingRoom.getPost_no();
            this.post_name = postService.getPost_Name(post_num);
            this.host_info = postService.getPost_Host_info(post_num);
            this.last_cht_msg = chattingService.getLastmsg(cht_room_no);
        }
    }
}


