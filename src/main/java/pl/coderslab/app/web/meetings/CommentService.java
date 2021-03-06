package pl.coderslab.app.web.meetings;

import org.springframework.stereotype.Service;
import pl.coderslab.app.models.Comment;
import pl.coderslab.app.models.Meeting;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repositories.CommentRepository;
import pl.coderslab.app.repositories.MeetingRepository;
import pl.coderslab.app.repositories.UserRepository;
import pl.coderslab.app.web.user.notifications.NotificationService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private MeetingRepository meetingRepository;
    private NotificationService notificationService;

    public CommentService(CommentRepository commentRepository,UserRepository userRepository,MeetingRepository meetingRepository, NotificationService notificationService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.notificationService = notificationService;
    }

    public Long countCommentsByMeeting(Meeting meeting) {
        return commentRepository.countAllByMeeting(meeting);
    }

    public List<Comment> getAllCommentsByParentAndMeeting(Comment parent, Meeting meeting) {
        return commentRepository.getAllByParentAndMeeting(parent, meeting);
    }

    public void addComment(CommentFormDTO newComment, String userEmail) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findByEmail(userEmail));

        if (newComment.getParentId()!=null) {
            comment.setParent(commentRepository.findOne(newComment.getParentId()));
        }
        comment.setText(newComment.getText());


        // ustawiamy update
        Meeting meeting = meetingRepository.findOne(newComment.getMeetingId());
        meeting.setUpdated(LocalDateTime.now());
        meetingRepository.save(meeting); //ustawiamy w ten sposób update.
        // zapisujemy komentarz
        comment.setMeeting(meeting);
        commentRepository.save(comment);

        // powiadomienie
        String notificationText = "Użytkownik <strong>"+userRepository.findByEmail(userEmail).getFullName()+"</strong> dodał nowy komentarz do wydarzenia <strong>"+meeting.getTitle()+"</strong>";
        String alertType = "info";
        // uwaga nowosc kopiujemy obiekt nie podajemy tylko referencji
        List<User> users = new ArrayList<>(meeting.getMembers());
        users.add(meeting.getOwner());
        users.remove(userRepository.findByEmail(userEmail));
        notificationService.addNotificationForUserList(notificationText,"meetings?id="+newComment.getMeetingId(),users,alertType);
    }

    public void removeCommentsByMeetingId(Long meetingId) {
        commentRepository.deleteAllByMeetingId(meetingId);
    }
}
