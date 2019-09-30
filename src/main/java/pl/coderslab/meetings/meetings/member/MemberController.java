package pl.coderslab.meetings.meetings.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.meetings.meetings.MeetingService;

import javax.servlet.http.HttpServletRequest;

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
        return "redirect:/meetings?id=" + memberDTO.getMeetingId();
    }

}
