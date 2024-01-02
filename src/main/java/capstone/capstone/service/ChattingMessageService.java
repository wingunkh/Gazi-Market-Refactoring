package capstone.capstone.service;

import capstone.capstone.domain.ChattingMessage;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Member;
import capstone.capstone.repository.ChattingMessageRepository;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChattingMessageService {
    private final ChattingMessageRepository chattingMessageRepository;

    private final ChattingRoomRepository chattingRoomRepository;

    private final MemberRepository memberRepository;

    public List<ChattingMessage> enterChattingRoom(Integer roomNum) {
        return chattingMessageRepository.findAllByChattingRoomRoomNumOrderByTime(roomNum);
    }

    public ChattingMessage send(Integer roomNum, Integer memberNum, ChattingMessage chattingMessage) {
        Optional<ChattingRoom> optionalChattingRoom = chattingRoomRepository.findById(roomNum);
        Optional<Member> optionalMember = memberRepository.findById(memberNum);

        if (optionalChattingRoom.isEmpty()) {
            throw new IllegalArgumentException("해당 채팅방이 존재하지 않습니다.");
        }

        if (optionalMember.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
        }

        chattingMessage.setChattingRoom(optionalChattingRoom.get());
        chattingMessage.setMember(optionalMember.get());
        chattingMessage.setTime(LocalDateTime.now());

        return chattingMessageRepository.save(chattingMessage);
    }
}