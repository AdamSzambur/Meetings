package pl.coderslab.app.web.meetings.chat;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.ChatMessage;

import java.util.List;

@RestController
@RequestMapping("/meetings/chat")
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public List<ChatMessage> getMessagesForMeeting(@RequestParam Long id) {
        return chatService.getAllChatMessagesByMeetingId(id);
    }


    @GetMapping("/length")
    public Long getLengthListMessagesForMeeting(@RequestParam Long id) {
        return chatService.getLengthListMessages(id);
    }

    @PostMapping
    public void addMessageForMeeting(@RequestBody ChatMessageDTO chatMessageDTO) {
        chatService.addNewChatMessage(chatMessageDTO);
    }
}


