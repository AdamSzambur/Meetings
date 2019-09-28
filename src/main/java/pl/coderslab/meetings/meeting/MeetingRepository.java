package pl.coderslab.meetings.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.meetings.models.Meeting;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime from, LocalDateTime to);

    List<Meeting> findAllByAddressContainsOrTitleContainsOrDescriptionContains(String phrase1, String phrase2,String phrase3);
}
