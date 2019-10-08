package pl.coderslab.app.models;

import pl.coderslab.app.web.user.messages.MessageDTO;

import javax.persistence.*;

@Entity
@Table(name ="outboxMessages")
public class OutboxMessage extends Message {

    public OutboxMessage() {
    }

    public OutboxMessage(MessageDTO messageDTO) {
        super.setTitle(messageDTO.getTitle());
        super.setText(messageDTO.getText());
        this.setSender(messageDTO.getSender());
        this.setRecipient(messageDTO.getRecipient());
    }

    @ManyToOne
    private User sender;

    @OneToOne
    private User recipient;

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
}
