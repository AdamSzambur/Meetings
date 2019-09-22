package pl.coderslab.meetings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.meetings.models.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
