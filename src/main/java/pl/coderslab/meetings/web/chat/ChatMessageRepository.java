package pl.coderslab.meetings.web.chat;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatMessageRepository {
    private static List<ChatMessage> chatMessages = new ArrayList<>();

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        ChatMessageRepository.chatMessages = chatMessages;
    }

    public void addChatMessage(ChatMessage chatMessage) {
        ChatMessageRepository.chatMessages.add(chatMessage);
    }


}
