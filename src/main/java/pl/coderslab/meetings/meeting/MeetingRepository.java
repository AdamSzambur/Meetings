package pl.coderslab.meetings.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.meetings.meeting.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime from, LocalDateTime to);
}
