package pl.coderslab.app.web.user.meetings;


import com.sun.istack.internal.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.app.models.Meeting;
import pl.coderslab.app.web.meetings.MeetingDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MeetingEditDTO extends MeetingDTO {

    private Boolean sendNotification = true;

    private Boolean sendEmail = false;

    public MeetingEditDTO() {
    }

    public MeetingEditDTO(Meeting meeting) {
        super.setId(meeting.getId());
        super.setTitle(meeting.getTitle());
        super.setMeetTime(meeting.getMeetTime());
        super.setDescription(meeting.getDescription());
        super.setAddress(meeting.getAddress());
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Boolean getSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(Boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
