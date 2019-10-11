package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.AbstractMessage;
import pl.coderslab.app.models.OutboxMessage;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {

    List<OutboxMessage> findAllByRecipientId(Long recipientId);

    List<AbstractMessage> findAllBySenderId(Long senderId);

    List<AbstractMessage> findAllBySenderIdAndTitleContainsOrRecipientEmail(Long senderId, String phrase1, String phrase2);


}
