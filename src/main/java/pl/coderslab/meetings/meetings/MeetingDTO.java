package pl.coderslab.meetings.meetings;


import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MeetingDTO {

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime meetTime;

    private String description;

    @NotBlank
    private String address;


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
