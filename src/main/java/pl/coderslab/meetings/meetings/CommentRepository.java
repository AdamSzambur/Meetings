package pl.coderslab.meetings.meetings;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.meetings.models.Comment;
import pl.coderslab.meetings.models.Meeting;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countAllByMeeting(Meeting meeting);
    List<Comment> getAllByParentAndMeeting(Comment parent, Meeting meeting);

}
