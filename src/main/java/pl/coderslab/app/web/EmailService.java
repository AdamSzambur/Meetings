package pl.coderslab.app.web;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Service
public class EmailService {

    HttpServletRequest request;

    public EmailService(HttpServletRequest request) {
        this.request = request;
    }

    public void sendEmail(String email, String title, String msg) throws Exception {
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
}
