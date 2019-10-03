package pl.coderslab.app.meetings.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.meetings.MeetingService;

@Controller
@RequestMapping(value = "/meetings/member")
public class MemberController {

    MeetingService meetingService;

    public MemberController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public String toogleMemberInMeeting(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        meetingService.toogleMemberInMeeting(memberDTO);
        return "redirect:/app?id=" + memberDTO.getMeetingId();
    }

}
