package pl.coderslab.app.models;


import javax.persistence.*;
import javax.validation.constraints.Size;
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

    private LocalDateTime updated;

    @Column(nullable = false)
    private LocalDateTime meetTime;

    @Column(length = 500)
    @Size(max = 500)
    private String description;

    @Column(nullable = false)
    private String address;


    private Double latitude;

    private Double longitude;

    @ManyToMany
    private List<User> members;

    @OneToMany(mappedBy = "meeting")
    private List<Comment> comments;

    @Transient
    private Long commentsNumber;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

//    @PreUpdate ze względu na to ze dodaję liste komentarzy do tej encji nie moge tego uzyc.
    public void preUpdate() { updated = LocalDateTime.now();}

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Long commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setBase64fromOwnerAvatar() {
        owner.setBase64Image(Base64.getEncoder().encodeToString(owner.getAvatar()));
    }

    public void addMember(User member) {
        this.members.add(member);
    }

    public void removeMember(User member) {
        this.members.remove(member);
    }
}
