package pl.coderslab.meetings.meetings.member;


import javax.validation.constraints.Size;

public class MemberDTO {


    private Long meetingId;

    private Long userId;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
