package pl.coderslab.meetings.meeting;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.meeting.Meeting;
import pl.coderslab.meetings.meeting.MeetingRepository;
import pl.coderslab.meetings.user.UserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MeetingService {

    private MeetingRepository meetingRepository;
    private UserService userService;

    public MeetingService(UserService userService,  MeetingRepository meetingRepository) {
        this.userService = userService;
        this.meetingRepository = meetingRepository;
    }

    public Meeting getMeetingById(Long id) {
        return meetingRepository.getOne(id);
    }

    public List<Meeting> getMeetingsNext7Days() {
        return meetingRepository.getAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime.now(),
                LocalDateTime.now().plusDays(7));
    }
}
