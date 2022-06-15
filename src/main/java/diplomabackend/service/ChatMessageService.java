package diplomabackend.service;

import diplomabackend.domain.Message;
import diplomabackend.enums.MessageStatus;
import diplomabackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    MessageRepository repository;

    public Message save(Message chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }
}
