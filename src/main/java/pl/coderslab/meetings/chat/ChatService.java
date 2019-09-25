package pl.coderslab.meetings.chat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.meetings.user.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private UserService userService;
    private ChatMessageRepository chatMessageRepository;


    public ChatService(UserService userService, ChatMessageRepository chatMessageRepository) {
        this.userService = userService;
        this.chatMessageRepository = chatMessageRepository;
//        chatMessages.add(new ChatMessage(1L,10L,"Adam Szamburski", null, "To jest pierwsza wiadomośc na czacie :)", LocalDateTime.now()));
//        chatMessages.add(new ChatMessage(1L,14L,"Łukasz Farys", null, "To jest druga wiadomośc na czacie :)", LocalDateTime.now()));
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.getChatMessages();
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        chatMessageRepository.setChatMessages(chatMessages);
    }

    public List<ChatMessage> getAllChatMessagesByMeetingId(Long id) {
        return chatMessageRepository.getChatMessages()
                .stream().
                        filter(s -> s.getMeetingId() == id)
                .collect(Collectors.toList());
    }

    public ChatMessage getLastChatMessageByMeetingId(Long id) {
        return chatMessageRepository.getChatMessages()
                .stream()
                .filter(s -> s.getMeetingId() == id)
                .max(Comparator.comparing(ChatMessage::getCreated))
                .get();
    }

    public void deleteAllChatMessagesByMeetingId(Long id) {
        chatMessageRepository.getChatMessages().removeIf(m -> m.getMeetingId() == id);
    }

    public void addNewChatMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = new ChatMessage(
                chatMessageDTO.getMeetingId(),
                chatMessageDTO.getUserId(),
                userService.getUserById(chatMessageDTO.getUserId()).getFullName(),
                userService.getUserById(chatMessageDTO.getUserId()).getBase64Image(),
                chatMessageDTO.getMessage(),
                LocalDateTime.now());
        chatMessageRepository.addChatMessage(chatMessage);
    }

    @Scheduled(fixedDelay = 20000)
    private void chatGarbageCollector() {
        Long secondsBetween = Duration.between(
                getLastChatMessageByMeetingId(1L).getCreated(), LocalDateTime.now()).getSeconds();
//        System.out.println("Róznica sekund pomiędzy aktualnym czasem a data ostatniego wpisu to : "+secondsBetween);
        if (secondsBetween > 60) {
            System.out.println("Kasujemy zwartość czatu");
            deleteAllChatMessagesByMeetingId(1L);
        }
    }
}
