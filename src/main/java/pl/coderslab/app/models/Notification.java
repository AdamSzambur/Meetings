package pl.coderslab.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(value= {"user"})
@Entity
@Table(name = "notifications")
public class Notification extends AbstractEntity {

    @ManyToOne
    private User user;

    @Transient
    private Long ownerId;

    private String text;

    private String url;

    private String status;

    private LocalDateTime created;

    public Notification() {
        this.status = "unread";
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
