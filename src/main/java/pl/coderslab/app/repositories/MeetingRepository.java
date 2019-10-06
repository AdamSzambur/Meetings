package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.Meeting;
import pl.coderslab.app.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByMeetTimeBetweenOrderByMeetTime(LocalDateTime from, LocalDateTime to);
    List<Meeting> findAllByAddressContainsOrTitleContainsOrDescriptionContains(String phrase1, String phrase2,String phrase3);

    List<Meeting> findAllByOwnerOrderByMeetTime(User owner);

    List<Meeting> findAllByMembersContainingOrderByMeetTime(User member);
}
