package pl.coderslab.app.web.user.messages;

import pl.coderslab.app.models.*;
import pl.coderslab.app.repositories.OutboxRepository;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
public class MessageDTO {

    public MessageDTO() {
    }

    public MessageDTO(AbstractMessage message) {
        if (message instanceof InboxMessage) {
            this.setRecipient(((InboxMessage) message).getRecipient());
            this.setSender(((InboxMessage) message).getSender());
        }
        if (message instanceof OutboxMessage) {
            this.setRecipient(((OutboxMessage) message).getRecipient());
            this.setSender(((OutboxMessage) message).getSender());
        }
        this.setId(message.getId());
        this.setTitle(message.getTitle());
        this.setText(message.getText());
    }

    private Long id;

    @NotNull
    private User sender;

    @NotNull
    private User recipient;

    @Size(min=1, max = 255, message = "Wiadomość musi mieć podany tytuł nie dłuższy niż 255 znaków")
    private String title;

    @Size(max=1000, message="Maksymalna długość wiadomości to 1000 znaków")
    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
