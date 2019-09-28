package pl.coderslab.meetings.meeting;


import com.sun.istack.internal.NotNull;
import pl.coderslab.meetings.models.AbstractEntity;
import pl.coderslab.meetings.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

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
