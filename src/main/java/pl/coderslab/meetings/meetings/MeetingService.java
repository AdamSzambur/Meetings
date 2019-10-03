package pl.coderslab.meetings.meetings;

import org.springframework.stereotype.Service;
import pl.coderslab.meetings.FinderFormDTO;
import pl.coderslab.meetings.meetings.DistanceJsonStructure.Distance;
import pl.coderslab.meetings.meetings.member.MemberDTO;
import pl.coderslab.meetings.models.Comment;
import pl.coderslab.meetings.models.Meeting;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeetingService {

    private MeetingRepository meetingRepository;
    private UserService userService;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public MeetingService(MeetingRepository meetingRepository, UserService userService
            , CommentRepository commentRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Meeting getMeetingById(Long id, boolean alldata) {
        Meeting result = meetingRepository.findOne(id);
        if (alldata) {
            result.getMembers().size();
            result.setBase64fromOwnerAvatar();
            result.setComments(commentRepository.getAllByParentAndMeeting(null,result));
            // w zasadzie nieograniczona ilosc komentarzy do komentarzy
            for (Comment comment : result.getComments()) {
                comment.setBase64fromUserAvatar();
                comment.setChildren(getChildrenLoop(comment.getChildren()));
            }
        }
        return result;
    }

    // metoda rekurencyjna
    public List<Comment> getChildrenLoop(List<Comment> childrenList) {
        for (Comment child : childrenList) {
            child.setBase64fromUserAvatar();
            child.setChildren(getChildrenLoop(child.getChildren()));
        }
        return childrenList;
    }

    public List<Meeting> getMeetingsNext7Days() {
        List<Meeting> result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTime(LocalDateTime.now(),
                LocalDateTime.now().plusDays(7));
        result.forEach(m->{
            m.getMembers().size();
            m.setCommentsNumber(commentRepository.countAllByMeeting(m));
            m.setBase64fromOwnerAvatar();
        });

        return result ;
    }

    public List<Meeting> getMeetingByDate(String selectedDate) {
        LocalDateTime date0 = LocalDate.parse(selectedDate.replace('.', '/'),
                DateTimeFormatter.ofPattern("M/d/yyyy")).atStartOfDay();
        LocalDateTime date1 = date0.plusDays(1);
        List<Meeting> result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTime(date0, date1);
        result.forEach(m->{
            m.getMembers().size();
            m.setCommentsNumber(commentRepository.countAllByMeeting(m));
            m.setBase64fromOwnerAvatar();
        });
        return result;
    }

    public List<Meeting> getMeetingByFinderForm(FinderFormDTO finderFormDTO) {
        List<Meeting> result;
        if (finderFormDTO.getFindPhrase() == null) {
            result = meetingRepository.findAllByMeetTimeBetweenOrderByMeetTime(LocalDateTime.now(),
                    LocalDateTime.now().plusYears(10));
        } else {
            result = meetingRepository.findAllByAddressContainsOrTitleContainsOrDescriptionContains(
                    finderFormDTO.getFindPhrase(),finderFormDTO.getFindPhrase(),finderFormDTO.getFindPhrase());
        }
        result =result.stream()
                .filter(m->getDistance(finderFormDTO,m.getAddress())<=finderFormDTO.getDistance())
                .collect(Collectors.toList());
        result.forEach(m->{
            m.getMembers().size();
            m.setCommentsNumber(commentRepository.countAllByMeeting(m));
            m.setBase64fromOwnerAvatar();
        });
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

        meeting.setUpdated(LocalDateTime.now());
        meetingRepository.save(meeting);
    }

    public void toogleMemberInMeeting(MemberDTO memberDTO) {
        Meeting meeting = meetingRepository.findOne(memberDTO.getMeetingId());
        User user = userRepository.findOne(memberDTO.getUserId());
        meeting.getMembers().size();

        if (meeting.getMembers().contains(user)) {
            System.out.println("Usuwamy uzytkownika z listy członków");
            meeting.removeMember(user);
        } else {
            System.out.println("Dodajemy uzytkownika do listy członków");
            meeting.addMember(user);
        }
        meetingRepository.save(meeting);
    }

    public List<Meeting> getMeetingByOwner(User owner) {
        List<Meeting> result = meetingRepository.findAllByOwnerOrderByMeetTime(owner);
        result.forEach(m->{
            m.getMembers().size();
            m.setCommentsNumber(commentRepository.countAllByMeeting(m));
            m.setBase64fromOwnerAvatar();
        });
        return result;
    }

    public List<Meeting> getMeetingsByMember(User member) {
        List<Meeting> result = meetingRepository.findAllByMembersContainingOrderByMeetTime(member);
        result.forEach(m->{
            m.getMembers().size();
            m.setCommentsNumber(commentRepository.countAllByMeeting(m));
            m.setBase64fromOwnerAvatar();
        });
        return result;
    }
}
