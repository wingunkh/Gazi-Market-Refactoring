package capstone.capstone.service;

import capstone.capstone.domain.ChattingMessage;
import capstone.capstone.repository.ChattingMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingMessageService {
    private final ChattingMessageRepository chattingMessageRepository;

    public List<ChattingMessage> enterChattingRoom(Integer roomNum) {
        return chattingMessageRepository.findAllByChattingRoomRoomNumOrderByTime(roomNum);
    }

    public ChattingMessage send(ChattingMessage chattingMessage) {
        chattingMessage.setTime(LocalDateTime.now());

        return chattingMessageRepository.save(chattingMessage);
    }
}