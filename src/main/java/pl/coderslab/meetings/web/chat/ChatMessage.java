package pl.coderslab.meetings.web.chat;

import java.time.LocalDateTime;

public class ChatMessage {

    private Long meetingId;

    private Long userId;

    private String userName;

    private String userAvatar;

    private String message;

    private LocalDateTime created;


    public ChatMessage() {
    }

    public ChatMessage(Long meetingId, Long userId, String userName, String userAvatar, String message, LocalDateTime created) {
        this.meetingId = meetingId;
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.message = message;
        this.created = created;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "meetingId=" + meetingId +
                "userId=" + userId +
                "userName=" + userName +
                "userAvatar=" + userAvatar +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
