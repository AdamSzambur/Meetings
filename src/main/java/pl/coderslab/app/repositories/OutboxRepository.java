package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.InboxMessage;
import pl.coderslab.app.models.Message;
import pl.coderslab.app.models.OutboxMessage;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {

    List<OutboxMessage> findAllByRecipientId(Long recipientId);

    List<OutboxMessage> findAllBySenderId(Long senderId);

    List<Message> findAllBySenderIdAndTitleContainsOrRecipientEmail(Long senderId, String phrase1, String phrase2);


}
