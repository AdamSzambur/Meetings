package pl.coderslab.app.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Meeting meeting;

    private LocalDateTime created;

    @Size(max=60, message = "Maksymalna długość komentarza to 60 znaków")
    private String text;

    // dodajemy mozliwosc komentowania samych komentarzy.
    @ManyToOne
    private Comment parent;
    @OneToMany(mappedBy = "parent")
    private List<Comment> children;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public void setBase64fromUserAvatar() {
        user.setBase64Image(Base64.getEncoder().encodeToString(user.getAvatar()));
    }
}
