package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.Comment;
import pl.coderslab.app.models.Meeting;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countAllByMeeting(Meeting meeting);
    List<Comment> getAllByParentAndMeeting(Comment parent, Meeting meeting);
    void deleteAllByMeetingId(Long meetingId);
}
