package com.github.jbence1994.webshop.ai;

import com.github.jbence1994.webshop.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {

    @Id
    private Long id;

    private UUID conversationId;

    private String prompt;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static ChatMessage from(UUID conversationId, String prompt, User user) {
        var chatMessage = new ChatMessage();

        chatMessage.conversationId = conversationId;
        chatMessage.prompt = prompt;
        chatMessage.user = user;

        return chatMessage;
    }
}
