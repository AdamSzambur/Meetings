package pl.coderslab.app.web.meetings;


import com.sun.istack.internal.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.app.models.Meeting;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MeetingDTO {
    private Long id;

    @NotBlank
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime meetTime;

    @Size(max = 500)
    private String description;

    @NotBlank
    private String address;


    public MeetingDTO() {
    }

    public MeetingDTO(Meeting meeting) {
        this.id = meeting.getId();
        this.title = meeting.getTitle();
        this.meetTime = meeting.getMeetTime();
        this.description = meeting.getDescription();
        this.address = meeting.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(LocalDateTime meetTime) {
        this.meetTime = meetTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
