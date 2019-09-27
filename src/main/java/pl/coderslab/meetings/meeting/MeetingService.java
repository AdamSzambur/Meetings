package pl.coderslab.meetings.meeting;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import pl.coderslab.meetings.meeting.Meeting;
import pl.coderslab.meetings.meeting.MeetingRepository;
import pl.coderslab.meetings.user.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return meetingRepository.findAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime.now(),
                LocalDateTime.now().plusDays(7));
    }

    public List<Meeting> getMeetingByDate(String selectedDate) {
        LocalDateTime date0 = LocalDate.parse(selectedDate.replace('.','/'),
                DateTimeFormatter.ofPattern("M/d/yyyy")).atStartOfDay();
        LocalDateTime date1 = date0.plusDays(1);
        return meetingRepository.findAllByMeetTimeBetweenOrderByMeetTimeDesc(date0,date1);
    }
}
