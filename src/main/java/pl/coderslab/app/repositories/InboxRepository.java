package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.InboxMessage;

import java.util.List;

public interface InboxRepository extends JpaRepository<InboxMessage, Long> {

    List<InboxMessage> findAllByRecipientId(Long recipientId);

    List<InboxMessage> findAllBySenderId(Long senderId);

    Long countByRecipientIdAndReaded(Long recipientId, Boolean readed);
}
