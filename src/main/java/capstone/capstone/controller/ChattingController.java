package capstone.capstone.controller;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.service.ChattingRoomService;
import capstone.capstone.service.ChattingService;
import capstone.capstone.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ChattingController {
    @Autowired
    private ChattingService chattingService;
    @Autowired
    private ChattingRoomService chattingRoomService;

    @Autowired
    private PostService postService;

    @GetMapping("/chattingroom/guest/{guest_no}")    //테스트 완료, 고객 기존 채팅방 내역
    public List<ChattingRoomList> getAllChattingRoom(@PathVariable Integer guest_no) {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getguestAllChattingRoom(guest_no)) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        return chattingRoomList;
    }

    @GetMapping("/chattingroom")    //테스트 완료, 전체 채팅방 내역, 관리자
    public List<ChattingRoomList> getChattingRoom() {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getAllChattingRoom()) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        return chattingRoomList;
    }

    @GetMapping("/chattingroom/post/{post_no}/{guest_no}")   //테스트 완료, 포스트 채팅방 입장
    public List<Chatting> getChattingByNo(@PathVariable Integer post_no, @PathVariable Integer guest_no) {
        return chattingRoomService.getChattingRoom(post_no, guest_no);
    }

    @GetMapping("/chatting/{cht_room_no}")  //테스트 완료, 채팅방 입장
    public ChattingList getAllChattingDate(@PathVariable Integer cht_room_no) {
        ChattingList chattingList = new ChattingList(chattingService.getAllChattingDate(cht_room_no));
        chattingList.setPost_title(chattingRoomService.getChattingPostTitle(cht_room_no));
        chattingList.setinfo(chattingRoomService.getHostInfo(cht_room_no), chattingRoomService.getGuestInfo(cht_room_no));
        return chattingList;
    }

    @PostMapping("/chatting")
    public Chatting createChatting(@RequestBody Chatting chatting) {
        chatting.setCht_time(LocalDateTime.now());
        return chattingService.createChatting(chatting);
    }

    @Getter
    class ChattingRoomList {
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

    @Getter
    class ChattingList {
        List<Chatting> chattingList;
        String post_title;
        int host_no;
        int guest_no;

        public ChattingList(List<Chatting> chattingList) {
            this.chattingList = chattingList;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public void setinfo(int host_no, int guest_no) {
            this.host_no = host_no;
            this.guest_no = guest_no;
        }
    }
}