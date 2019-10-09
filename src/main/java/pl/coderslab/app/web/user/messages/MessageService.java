package pl.coderslab.app.web.user.messages;

import org.springframework.stereotype.Service;
import pl.coderslab.app.models.*;
import pl.coderslab.app.repositories.InboxRepository;
import pl.coderslab.app.repositories.OutboxRepository;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Properties;

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

    public void sendEmail(HttpServletRequest request, String email, String title, String msg) throws Exception {
        String smtpServer = request.getServletContext().getInitParameter("smtpServer");
        int port = Integer.parseInt(request.getServletContext().getInitParameter("port"));
        String userid = request.getServletContext().getInitParameter("userEmail");
        String password = request.getServletContext().getInitParameter("password");
        String contentType = "text/html";
        String subject = title;
        String from = request.getServletContext().getInitParameter("userEmail");
        String to = email;//some invalid address
        String bounceAddr = request.getServletContext().getInitParameter("userEmail");
        String body = msg;

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.from", bounceAddr);

        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userid,password);
                    }
                });

        MimeMessage message = new MimeMessage(mailSession);
        message.addFrom(InternetAddress.parse(from));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(body, contentType);

        Transport transport = mailSession.getTransport();
        try {
            System.out.println("Sending ....");
            transport.connect(smtpServer, port, userid, password);
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            System.out.println("Sending done ...");
        } catch (Exception e) {
            System.err.println("Error Sending: ");
            e.printStackTrace();

        }
        transport.close();
    }

    public List<pl.coderslab.app.models.Message> getAllMessagesContainsFraze(Long userId, String box, String searchFraze) {
        if (box.equals("inbox")) {
            return inboxRepository.findAllByRecipientIdAndTitleContainsOrSenderEmail(userId,searchFraze,searchFraze);
        } else {
            return outboxRepository.findAllBySenderIdAndTitleContainsOrRecipientEmail(userId,searchFraze,searchFraze);
        }
    }
}
