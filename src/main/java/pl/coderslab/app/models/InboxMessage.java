package pl.coderslab.app.models;

import pl.coderslab.app.web.user.messages.MessageDTO;

import javax.persistence.*;

@Entity
@Table(name ="inboxMessages")
public class InboxMessage extends AbstractMessage {

    public InboxMessage() {
    }

    public InboxMessage(MessageDTO messageDTO) {
        super.setTitle(messageDTO.getTitle());
        super.setText(messageDTO.getText());
        this.setSender(messageDTO.getSender());
        this.setRecipient(messageDTO.getRecipient());
    }

    @ManyToOne
    private User recipient;

    @OneToOne
    private User sender;

    private Boolean readed = false;

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }
}
