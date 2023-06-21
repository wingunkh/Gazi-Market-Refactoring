package capstone.capstone.controller;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.extendedDomain.ChattingWithName;
import capstone.capstone.service.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://52.78.130.186")
@RestController
@RequestMapping("/api")
public class ChattingController {
    @Autowired
    private ChattingService chattingService;

    @Autowired
    private ChattingRoomService chattingRoomService;

    @Autowired
    UserMemberService userMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    // 게시글에서 채팅방 입장(생성)
    @GetMapping("/chattingroom/post/{post_no}/{guest_no}")
    public ChattingList getChattingByNo(@PathVariable Integer post_no, @PathVariable Integer guest_no) {
        int cht_room_no = chattingRoomService.getChattingRoom(post_no, guest_no);
        ChattingList chattingList = new ChattingList(chattingService.getAllChattingDate(cht_room_no));
        chattingList.setting(cht_room_no);
        System.out.println(guest_no + "사용자가 " + post_no + "번 게시글 " + cht_room_no + "번 채팅방 입장");
        return chattingList;
    }

    // 채팅방 목록에서 채팅방 입장
    @GetMapping("/chatting/{cht_room_no}")
    public ChattingList getAllChattingDate(@PathVariable Integer cht_room_no) {
        ChattingList chattingList = new ChattingList(chattingService.getAllChattingDate(cht_room_no));
        chattingList.setting(cht_room_no);
        System.out.println(cht_room_no + "번 채팅방 입장");
        return chattingList;
    }

    // 채팅 전송
    @PostMapping("/chatting")
    public Chatting createChatting(@RequestBody Chatting chatting) {
        chatting.setCht_time(LocalDateTime.now().plusHours(9));
        System.out.println(chatting.getCht_room_num() + "번 채팅방 -> " + chatting.getCht_member() + "번 사용자: " + chatting.getCht_text());
        return chattingService.createChatting(chatting);
    }

    // 전체 채팅방 목록 리턴(사용자)
    @GetMapping("/chattingroom/guest/{guest_no}")
    public List<ChattingRoomList> getAllChattingRoom(@PathVariable Integer guest_no) {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getguestAllChattingRoom(guest_no)) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        return chattingRoomList;
    }

    // 전체 채팅방 목록 리턴(관리자)
    @GetMapping("/chattingroom")
    public List<ChattingRoomList> getChattingRoom() {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getAllChattingRoom()) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        System.out.println("전체 활성 채팅방 목록 반환");
        return chattingRoomList;
    }

    @Getter
    class ChattingRoomList {
        int cht_room_no;
        int post_num;
        String host_info;
        String post_name;
        String last_cht_msg;
        String pictureURL;
        LocalDateTime last_cht_time;

        public ChattingRoomList(ChattingRoom chattingRoom) {
            this.cht_room_no = chattingRoom.getCht_room_num();
            this.post_num = chattingRoom.getPost_num();
            this.post_name = postService.getPost_Name(post_num);
            this.host_info = postService.getPost_Host_info(post_num);
            this.last_cht_msg = chattingService.getLastmsg(cht_room_no);
            this.last_cht_time = chattingService.getLasttime(cht_room_no);
            this.pictureURL = chattingRoomService.getChattingPostPicture(cht_room_no);
        }
    }

    @Getter
    class ChattingList {
        int cht_room_no;
        List<ChattingWithName> chattingList;
        String post_title;
        int host_no;
        int guest_no;
        String pictureURL;

        public ChattingList(List<Chatting> chattingList) {
            this.chattingList = new ArrayList<>();

            for(Chatting chat : chattingList){
                ChattingWithName chattingWithName = new ChattingWithName(chat, userService.findName(chat.getCht_member()));
                chattingWithName.setCht_member_profile(userMemberService.showProfileImage(chattingWithName.getCht_member()));
                this.chattingList.add(chattingWithName);
            }
        }

        public void setting(Integer cht_room_no){
            this.post_title = chattingRoomService.getChattingPostTitle(cht_room_no);
            this.host_no = chattingRoomService.getHostInfo(cht_room_no);
            this.guest_no = chattingRoomService.getGuestInfo(cht_room_no);
            this.cht_room_no = cht_room_no;
            this.pictureURL = chattingRoomService.getChattingPostPicture(cht_room_no);
        }

        public void setCht_room_no(int cht_room_no) {this.cht_room_no = cht_room_no; }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public void setPictureURL(String pictureURL) { this.pictureURL = pictureURL; }

    }
}