package pl.coderslab.app.web.user.messages;

import org.springframework.stereotype.Service;
import pl.coderslab.app.models.*;
import pl.coderslab.app.repositories.InboxRepository;
import pl.coderslab.app.repositories.OutboxRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private InboxRepository inboxRepository;
    private OutboxRepository outboxRepository;

    public MessageService(InboxRepository inboxRepository, OutboxRepository outboxRepository) {
        this.inboxRepository = inboxRepository;
        this.outboxRepository = outboxRepository;
    }

    public void deleteInboxMessage(Long inboxMessageId) {
        inboxRepository.delete(inboxMessageId);
    }

    public void deleteOutboxMessage(Long outboxMessageId) {
        outboxRepository.delete(outboxMessageId);
    }

    public void sendMessage(MessageDTO messageDTO) {
        // musimy zapisac wiadomosc w dwóch skrzynkach
        OutboxMessage outboxMessage = new OutboxMessage(messageDTO);
        InboxMessage inboxMessage = new InboxMessage(messageDTO);
        outboxRepository.save(outboxMessage);
        inboxRepository.save(inboxMessage);
    }

    public Long getNewUnreadedMessagesByRecipient(Long recipientId) {
        return inboxRepository.countByRecipientIdAndReaded(recipientId, false);
    }

    public List<AbstractMessage> getAllMessagesContainsFraze(Long userId, String box, String searchFraze) {
        if (box.equals("inbox")) {
            return inboxRepository.findAllByRecipientIdAndTitleContainsOrSenderEmail(userId,searchFraze,searchFraze);
        } else {
            return outboxRepository.findAllBySenderIdAndTitleContainsOrRecipientEmail(userId,searchFraze,searchFraze);
        }
    }

    public List<AbstractMessage> getAllMessagesByUser(Long userId, String box) {
        if (box.equals("inbox")) {
            return inboxRepository.findAllByRecipientId(userId);
        } else {
            return  outboxRepository.findAllBySenderId(userId);
        }
    }

    public void deleteMessage(Long messageId, String box) {
        if (box.equals("inbox")) {
            inboxRepository.delete(messageId);
        } else {
            outboxRepository.delete(messageId);
        }
    }

    public void deleteMessages(List<Long> selectedMessages, String box) {
        if (box.equals("inbox")) {
            selectedMessages.forEach(m -> inboxRepository.delete(m));
        } else {
            selectedMessages.forEach(m -> outboxRepository.delete(m));
        }
    }

    public AbstractMessage getMessageById(Long id, String box) {
        if (box.equals("inbox")) {
            return inboxRepository.findOne(id);
        } else {
            return outboxRepository.findOne(id);
        }
    }
}
