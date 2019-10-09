package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.InboxMessage;
import pl.coderslab.app.models.AbstractMessage;


import java.util.List;

public interface InboxRepository extends JpaRepository<InboxMessage, Long> {

    List<AbstractMessage> findAllByRecipientId(Long recipientId);

    List<InboxMessage> findAllBySenderId(Long senderId);

    List<AbstractMessage> findAllByRecipientIdAndTitleContainsOrSenderEmail(Long recipientId, String phrase1, String phrase2);

    Long countByRecipientIdAndReaded(Long recipientId, Boolean readed);


}
