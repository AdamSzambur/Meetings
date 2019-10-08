package pl.coderslab.app.web.user.messages;

import org.springframework.stereotype.Service;
import pl.coderslab.app.models.*;
import pl.coderslab.app.repositories.InboxRepository;
import pl.coderslab.app.repositories.NotificationRepository;
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

    public List<InboxMessage> getAllInboxMessagesByRecipientId(Long recipientId) {
        return inboxRepository.findAllByRecipientId(recipientId);
    }

    public List<OutboxMessage> getAllOutboxMessagesByRecipientId(Long senderId) {
        return outboxRepository.findAllBySenderId(senderId);
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
}
