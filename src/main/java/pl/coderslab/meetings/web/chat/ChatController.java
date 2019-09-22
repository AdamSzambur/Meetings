package pl.coderslab.meetings.web.chat;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.meetings.web.chat.ChatMessage;
import pl.coderslab.meetings.services.MeetingService;
import pl.coderslab.meetings.web.chat.ChatService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public List<ChatMessage> getMessagesForMeeting() {
        return chatService.getAllChatMessagesByMeetingId(1L);
    }

    @PostMapping("/")
    public void addMessageForMeeting(@RequestBody ChatMessageDTO chatMessageDTO) {
        chatService.addNewChatMessage(chatMessageDTO);
    }
}


