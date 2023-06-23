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
    private UserMemberService userMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    // 게시글에서 채팅방 입장(생성)
    @GetMapping("/chattingroom/post/{post_num}/{guest_num}")
    public ChattingList enterChattingRoom(@PathVariable Integer post_num, @PathVariable Integer guest_num) {
        int cht_room_num = chattingRoomService.enterChattingRoom(post_num, guest_num);
        ChattingList chattingList = new ChattingList(chattingService.enterChattingRoom(cht_room_num));
        chattingList.setting(cht_room_num);
        System.out.println(guest_num + "사용자가 " + post_num + "번 게시글 " + cht_room_num + "번 채팅방 입장");
        return chattingList;
    }

    // 채팅방 목록에서 채팅방 입장
    @GetMapping("/chatting/{cht_room_num}")
    public ChattingList enterChattingRoom(@PathVariable Integer cht_room_num) {
        ChattingList chattingList = new ChattingList(chattingService.enterChattingRoom(cht_room_num));
        chattingList.setting(cht_room_num);
        System.out.println(cht_room_num + "번 채팅방 입장");
        return chattingList;
    }

    // 채팅 전송
    @PostMapping("/chatting")
    public Chatting sendMessage(@RequestBody Chatting chatting) {
        chatting.setCht_time(LocalDateTime.now().plusHours(9));
        System.out.println(chatting.getCht_room_num() + "번 채팅방 -> " + chatting.getCht_member() + "번 사용자: " + chatting.getCht_text());
        return chattingService.sendMessage(chatting);
    }

    // 전체 채팅방 목록 리턴(사용자)
    @GetMapping("/chattingroom/guest/{guest_num}")
    public List<ChattingRoomList> getAllChattingRoom(@PathVariable Integer guest_num) {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getAllChattingRoom(guest_num)) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        return chattingRoomList;
    }

    // 전체 채팅방 목록 리턴(관리자)
    @GetMapping("/chattingroom")
    public List<ChattingRoomList> getAllChattingRoom() {
        List<ChattingRoomList> chattingRoomList = new ArrayList<ChattingRoomList>();
        for (ChattingRoom chattingRoom : chattingRoomService.getAllChattingRoom()) {
            chattingRoomList.add(new ChattingRoomList(chattingRoom));
        }
        System.out.println("전체 활성 채팅방 목록 반환");
        return chattingRoomList;
    }

    @Getter
    class ChattingRoomList {
        int cht_room_num;
        int post_num;
        String host_info;
        String post_name;
        String last_cht_msg;
        String pictureURL;
        LocalDateTime last_cht_time;

        public ChattingRoomList(ChattingRoom chattingRoom) {
            this.cht_room_num = chattingRoom.getCht_room_num();
            this.post_num = chattingRoom.getPost_num();
            this.post_name = postService.getPostName(post_num);
            this.host_info = postService.getHostInfo(post_num);
            this.last_cht_msg = chattingService.getLastMsg(cht_room_num);
            this.last_cht_time = chattingService.getLastTime(cht_room_num);
            this.pictureURL = chattingRoomService.getChattingPostPicture(cht_room_num);
        }
    }

    @Getter
    class ChattingList {
        int cht_room_num;
        List<ChattingWithName> chattingList;
        String post_title;
        int host_num;
        int guest_num;
        String pictureURL;

        public ChattingList(List<Chatting> chattingList) {
            this.chattingList = new ArrayList<>();

            for(Chatting chat : chattingList){
                ChattingWithName chattingWithName = new ChattingWithName(chat, userService.findName(chat.getCht_member()));
                chattingWithName.setCht_member_profile(userMemberService.showProfileImage(chattingWithName.getCht_member()));
                this.chattingList.add(chattingWithName);
            }
        }

        public void setting(Integer cht_room_num){
            this.post_title = chattingRoomService.getChattingPostTitle(cht_room_num);
            this.host_num = chattingRoomService.getHostInfo(cht_room_num);
            this.guest_num = chattingRoomService.getGuestInfo(cht_room_num);
            this.cht_room_num = cht_room_num;
            this.pictureURL = chattingRoomService.getChattingPostPicture(cht_room_num);
        }

        public void setCht_room_num(int cht_room_num) {this.cht_room_num = cht_room_num; }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public void setPictureURL(String pictureURL) { this.pictureURL = pictureURL; }
    }
}