package pl.coderslab.app.meetings;


import javax.validation.constraints.Size;

public class CommentFormDTO {

    private Long meetingId;

    private Long parentId;

    @Size(max=60, message = "Maksymalna długość komentarza to 60 znaków")
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public CommentFormDTO(Long meetingId) {
        this.meetingId = meetingId;
    }

    public CommentFormDTO() {
    }
}
