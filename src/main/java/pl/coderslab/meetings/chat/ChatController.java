package pl.coderslab.meetings.chat;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.meetings.models.ChatMessage;

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


