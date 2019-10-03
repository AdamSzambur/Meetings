package pl.coderslab.app.web.meetings.chat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.app.repositories.MeetingRepository;
import pl.coderslab.app.models.ChatMessage;
import pl.coderslab.app.web.user.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private UserService userService;
    private MeetingRepository meetingRepository;
    private ChatMessageRepository chatMessageRepository;

    public ChatService(UserService userService, ChatMessageRepository chatMessageRepository, MeetingRepository meetingRepository) {
        this.userService = userService;
        this.chatMessageRepository = chatMessageRepository;
        this.meetingRepository = meetingRepository;
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
        for (Long meetingId : getListOfMeetingsIdInChat()) {
            Long secondsBetween = Duration.between(
                    getLastChatMessageByMeetingId(meetingId).getCreated(), LocalDateTime.now()).getSeconds();
            if (secondsBetween > 60) {
                System.out.println("Kasujemy zwartość czatu dla wydarzenia o id =" + meetingId);
                deleteAllChatMessagesByMeetingId(meetingId);
            }
        }
    }

    public Long getLengthListMessages(Long id) {
        return chatMessageRepository.getChatMessages().stream().filter(m->m.getMeetingId()==id).count();
    }

    public List<Long> getListOfMeetingsIdInChat() {
        List<Long> result = new ArrayList<>();
        for (ChatMessage message : chatMessageRepository.getChatMessages()) {
            if (!result.contains(message.getMeetingId())) {
                result.add(message.getMeetingId());
            }
        }
        return result;
    }
}
