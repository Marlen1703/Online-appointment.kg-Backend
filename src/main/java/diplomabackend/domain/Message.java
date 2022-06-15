package diplomabackend.domain;

import diplomabackend.enums.MessageStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        private String chatId;
        private String senderId;
        private String recipientId;
        private String senderName;
        private String recipientName;
        private String content;
        private Date timestamp;
        private MessageStatus status;

}
