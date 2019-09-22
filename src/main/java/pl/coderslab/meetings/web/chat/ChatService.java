package pl.coderslab.meetings.web.chat;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private UserService userService;

    private List<ChatMessage> chatMessages = new ArrayList<>();


    public ChatService(UserService userService) {
        this.userService = userService;

//        chatMessages.add(new ChatMessage(1L,10L,"Adam Szamburski", null, "To jest pierwsza wiadomośc na czacie :)", LocalDateTime.now()));
//        chatMessages.add(new ChatMessage(1L,14L,"Łukasz Farys", null, "To jest druga wiadomośc na czacie :)", LocalDateTime.now()));
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<ChatMessage> getAllChatMessagesByMeetingId(Long id) {
        return chatMessages.stream().filter(s->s.getMeetingId() == id).collect(Collectors.toList());
    }

    public void addNewChatMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = new ChatMessage(
                chatMessageDTO.getMeetingId(),
                chatMessageDTO.getUserId(),
                userService.getUserById(chatMessageDTO.getUserId()).getFullName(),
                userService.getUserById(chatMessageDTO.getUserId()).getBase64Image(),
               // null,
                chatMessageDTO.getMessage(),
                LocalDateTime.now());
        chatMessages.add(chatMessage);
    }








}
