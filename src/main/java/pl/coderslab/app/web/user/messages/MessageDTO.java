package pl.coderslab.app.web.user.messages;

import pl.coderslab.app.models.AbstractEntity;
import pl.coderslab.app.models.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class MessageDTO {

    @NotNull
    private User sender;

    @NotNull
    private User recipient;

    @Size(min=1, max = 255, message = "Wiadomość musi mieć podany tytuł nie dłuższy niż 255 znaków")
    private String title;

    @Column(length = 1000)
    @Size(max=1000, message="Maksymalna długość wiadomości to 1000 znaków")
    private String text;

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
