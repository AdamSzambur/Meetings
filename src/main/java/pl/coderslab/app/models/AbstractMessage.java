package pl.coderslab.app.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractMessage extends AbstractEntity {

    @Size(min=1, max = 255, message = "Wiadomość musi mieć podany tytuł nie dłuższy niż 255 znaków")
    private String title;

    @Column(length = 1000)
    @Size(max=1000, message="Maksymalna długość wiadomości to 1000 znaków")
    private String text;

    private LocalDateTime created;



    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
