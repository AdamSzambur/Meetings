package pl.coderslab.meetings.meetings;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.models.Comment;
import pl.coderslab.meetings.models.Meeting;
import pl.coderslab.meetings.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private MeetingRepository meetingRepository;

    public CommentService(CommentRepository commentRepository,UserRepository userRepository,MeetingRepository meetingRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
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

        Meeting meeting = meetingRepository.findOne(newComment.getMeetingId());
        meeting.setUpdated(LocalDateTime.now());
        meetingRepository.save(meeting); //ustawiamy w ten spsób update.
        comment.setMeeting(meeting);
        commentRepository.save(comment);
    }
}
