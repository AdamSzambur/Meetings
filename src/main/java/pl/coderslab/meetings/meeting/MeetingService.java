package pl.coderslab.meetings.meeting;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.FinderFormDTO;
import pl.coderslab.meetings.meeting.DistanceJsonStructure.Distance;
import pl.coderslab.meetings.models.Meeting;
import pl.coderslab.meetings.user.UserService;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeetingService {

    private MeetingRepository meetingRepository;
    private UserService userService;

    public MeetingService(UserService userService, MeetingRepository meetingRepository) {
        this.userService = userService;
        this.meetingRepository = meetingRepository;
    }

    public Meeting getMeetingById(Long id) {
        return meetingRepository.getOne(id);
    }

    public List<Meeting> getMeetingsNext7Days() {
        List<Meeting> result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime.now(),
                LocalDateTime.now().plusDays(7));
        result.forEach(Meeting::setBase64fromOwnerAvatar);
        return result ;
    }

    public List<Meeting> getMeetingByDate(String selectedDate) {
        LocalDateTime date0 = LocalDate.parse(selectedDate.replace('.', '/'),
                DateTimeFormatter.ofPattern("M/d/yyyy")).atStartOfDay();
        LocalDateTime date1 = date0.plusDays(1);
        List<Meeting> result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTimeDesc(date0, date1);
        result.forEach(Meeting::setBase64fromOwnerAvatar);
        return result;
    }

    public List<Meeting> getMeetingByFinderForm(FinderFormDTO finderFormDTO) {
        List<Meeting> result;
        if (finderFormDTO.getFindPhrase() == null) {
            result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTimeDesc(LocalDateTime.now(),
                    LocalDateTime.now().plusYears(10));
        } else {
            result = meetingRepository.findAllByAddressContainsOrTitleContainsOrDescriptionContains(
                    finderFormDTO.getFindPhrase(),finderFormDTO.getFindPhrase(),finderFormDTO.getFindPhrase());
        }
        result =result.stream()
                .filter(m->getDistance(finderFormDTO,m.getAddress())<=finderFormDTO.getDistance())
                .collect(Collectors.toList());
        result.forEach(Meeting::setBase64fromOwnerAvatar);
        return result;
    }


    private Long getDistance(FinderFormDTO finderFormDTO, String destinationAddres) {
        String googleURL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="
                + finderFormDTO.getLatitude() + "," + finderFormDTO.getLongitude()
                + "&destinations="
                + destinationAddres.replaceAll(" ","+")
                + "&key=AIzaSyC5EJjfoZUTXckzVuwbvm3Ke0SWYwoi6OI";

        Jsonb jsonb = JsonbBuilder.create();
        Distance distance = jsonb.fromJson(getJSONStringFromUrl(googleURL), Distance.class);


        return distance.getDistanceInKm();
    }

    private String getJSONStringFromUrl(String googleURL) {
        StringBuilder jsonFromGoogle = new StringBuilder();
        try {
            //System.out.println(googleURL);
            URL oracle = new URL(googleURL);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                //System.out.println(inputLine);
                jsonFromGoogle.append(inputLine);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return jsonFromGoogle.toString();
    }

    public void addMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        meeting.setAddress(meetingDTO.getAddress());
        meeting.setDescription(meetingDTO.getDescription());
        meeting.setMeetTime(meetingDTO.getMeetTime());
        meeting.setOwner(userService.getUserById(meetingDTO.getOwnerId()));
        meeting.setTitle(meetingDTO.getTitle());
        meetingRepository.save(meeting);
    }
}
