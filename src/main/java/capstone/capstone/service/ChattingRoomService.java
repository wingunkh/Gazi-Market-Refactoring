package capstone.capstone.service;

import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Member;
import capstone.capstone.domain.Post;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.MemberRepository;
import capstone.capstone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    private static ChattingRoom getChattingRoom(Optional<Post> optionalPost, Optional<Member> optionalMember) {
        ChattingRoom newChattingRoom = new ChattingRoom();

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        if (optionalMember.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
        }

        Post post = optionalPost.get();
        Member member = optionalMember.get();

        newChattingRoom.setPost(post);
        newChattingRoom.setHost(post.getMember());
        newChattingRoom.setGuest(member);

        return newChattingRoom;
    }

    public ChattingRoom createChattingRoom(Integer guestNum, Integer postNum) {
        ChattingRoom chattingRoom = chattingRoomRepository.findByGuestMemberNumAndPostPostNum(guestNum, postNum);

        if (chattingRoom != null) {
            return chattingRoom;
        }

        Optional<Post> optionalPost = postRepository.findById(postNum);
        Optional<Member> optionalMember = memberRepository.findById(guestNum);
        ChattingRoom newChattingRoom = getChattingRoom(optionalPost, optionalMember);

        return chattingRoomRepository.save(newChattingRoom);
    }

    public List<ChattingRoom> findAllChattingRooms() {
        return chattingRoomRepository.findAll();
    }

    public List<ChattingRoom> findAllChattingRoomsByHostNumOrGuestNum(Integer memberNum) {
        List<ChattingRoom> chattingRoomList = new ArrayList<>();

        chattingRoomList.addAll(chattingRoomRepository.findAllByHostMemberNum(memberNum));
        chattingRoomList.addAll(chattingRoomRepository.findAllByGuestMemberNum(memberNum));

        return chattingRoomList;
    }
}