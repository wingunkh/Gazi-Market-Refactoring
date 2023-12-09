package capstone.capstone.service;

import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Post;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;

    private final PostRepository postRepository;

    public ChattingRoom enterChattingRoom(Integer postNum, Integer guestNum) {
        ChattingRoom chattingRoom = chattingRoomRepository.findByPostNumAndGuestNum(postNum, guestNum);

        if (chattingRoom == null) {
            Optional<Post> optionalPost = postRepository.findById(postNum);
            ChattingRoom newChattingRoom = new ChattingRoom();

            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                newChattingRoom.setPostNum(post.getPostNum());
                newChattingRoom.setHostNum(post.getMember().getMemberNum());
                newChattingRoom.setGuestNum(guestNum);

                return chattingRoomRepository.save(newChattingRoom);
            } else
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        } else
            return chattingRoom;
    }

    public List<ChattingRoom> findAll() {
        return chattingRoomRepository.findAll();
    }

    public List<ChattingRoom> findAllByHostNumOrGuestNum(Integer memberNum) {
        return chattingRoomRepository.findAllByHostNumOrGuestNum(memberNum, memberNum);
    }
}