package pl.coderslab.meetings.models;


import pl.coderslab.meetings.web.chat.ChatMessage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meetings")
public class Meeting extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime meetTime;

    private String description;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "meetings")
    private List<User> members;

    @Transient
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
