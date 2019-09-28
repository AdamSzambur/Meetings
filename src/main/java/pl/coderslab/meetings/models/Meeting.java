package pl.coderslab.meetings.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name="meetings")
public class Meeting extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private User owner;

    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime meetTime;

    private String description;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "meetings")
    private List<User> members;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setBase64fromOwnerAvatar() {
        owner.setBase64Image(Base64.getEncoder().encodeToString(owner.getAvatar()));
    }

}
