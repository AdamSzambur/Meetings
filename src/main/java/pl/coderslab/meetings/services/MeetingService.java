package pl.coderslab.meetings.services;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.models.Meeting;
import pl.coderslab.meetings.repositories.MeetingRepository;
import javax.transaction.Transactional;

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

}
