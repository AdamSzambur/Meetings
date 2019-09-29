package pl.coderslab.meetings.meetings.chat;

import org.springframework.stereotype.Component;
import pl.coderslab.meetings.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatMessageRepository {
    private static List<ChatMessage> chatMessages = new ArrayList<>();
    List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
    void setChatMessages(List<ChatMessage> chatMessages) {
        ChatMessageRepository.chatMessages = chatMessages;
    }
    void addChatMessage(ChatMessage chatMessage) {
        ChatMessageRepository.chatMessages.add(chatMessage);
    }
}
