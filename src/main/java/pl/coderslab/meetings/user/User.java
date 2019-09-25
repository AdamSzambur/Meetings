package pl.coderslab.meetings.user;

import pl.coderslab.meetings.meeting.Meeting;
import pl.coderslab.meetings.AbstractEntity;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name="users")
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Meeting> myMeetings;

    @ManyToMany
    @JoinTable(name = "users_meetings")
    private List<Meeting> meetings;


    // domyslnie dopisujemy avatara do uzytkownika (mogło być ładniej ale nie wyszła tak źle).
    @Lob
    @Column(name="avatar", nullable=false, columnDefinition="mediumblob")
    private byte[] avatar;

    @Transient
    private String base64Image;

    @Transient
    private String defaultAvatar = "iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABmJLR0QA/wD/" +
            "AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4wkPFC4Xmow27AAABGFJREFUWMPtmGtsVEUUx39nbrdLi9SKjURTmygkGPF" +
            "daEsgWqSICSQ+iEgCIW5bSEEoEkXxhShGghje0tIuuy0p1CjxA+KDd6BI2+12W2IUhaCBBFEglIq023bvHT/cQoEVtEu3Ccn+v8zkzpmd3z" +
            "05Z87ZCzHFdDNr7b7/tlmx/YaOkIh3rq+DvGH2vLRmEI744SCpCKD1CULtNeRnHQbAXQv5mb0I6PFD7lAoqkqiT99ClLyI1kG0PgNokBSU9" +
            "EXrzTSd/pi5Yy/g9kF+Ri8AXvRGaW0KjvjlQCbaWgXsQ1vn0IAykkFnITIfET/NzdMozG6meD8UjIwi4OR5sHEprN3rJLHf64hMxgy9TO6w" +
            "XWG2i79UDEjLQKlykO946dE50U+KRm2PXv8DlAWO4A3MurTm07ByR/geb/0EyhuO4vFnAzDrk24dqbpl/YjAsm8NNFmICtL61xd2wvggQ2D" +
            "OGNtu4cauPdqqw9K/oYwnAVjzWhQBAZJu74PIw8AxZmb/CUDeVcG/cHLXvK31DCKHEAYy5R2ju8d1HzDOIYiKR1ut/8u+5XwIdAtIPJmjHN" +
            "EHbA+G0NYpRA3o8tima1/iSf0TgbvQ+iyzRwejDzh9eBA4ANzN+rosAO5LhylvdNms3gUzH7fnRnwa8BBaBwBYtTOKgEVVnYGvG9DWL8QZc" +
            "1m5K4FJg6FiCRTtt9dnj+4E3eFEZAZIkI62rQAU5vRSqfP4czCM1WhqCbYsoGDE8SvWi6vS6HPLfGA8ljmD3KFf906pK6mB6Vmw/BtF8oCn" +
            "EXkPUUlovQ/0IbvUMQRRI9BWCK3fwpVuw63ZA7NG9ZIHL3myLg1RzwDZIHd2xsAfoHcDW3ANvdKzFT/A7s3geb8HAV3vgneRPX+1xMGQx6a" +
            "i1AtofYTzTQsozGnCW5+IqITOy7kVV3oLq3bcRr/+HyByP6ZVxqljn/PmhDa7A6qGacN7AHD5NnjlKRABty8Ph2MJIgamuQelBiEyEMvaim" +
            "VuxwwdBxGMuHsQNQalxoE+iWX5UEYO0E6ofR5nf69k3rMW633hl3y3stjtg7ljbThvfSXxznWYZhGnT6TiSn+e802ZdLTnonUShmMZzsRtO" +
            "BO2oYylCImEOvL59cfBuNIn8XfzQKyQB4fTS0rqCty1ieRlQPH3EcbW9EWXF/wKyhvPUnIg87IsDd9T8JGDca64sOfF+y976ZrRbGg8h8e/" +
            "mJJqR+TBX/nTRS9OZMPBNkqrR17qB8Ne5sPwZxOv6q7e9kB5g3T+5nOUN3bgrh0BwKLPbiBLyxsa8fiLrgnXXX110h7LAlsoC+zsvPgjjMG" +
            "SAw+C3IsZWseneyP+X3GFDjfYo9mxBlHZrNx+KyIRV4uplAWORq0B3nDQpKT6ies2T9fPceMOhGTKApuA9h6F07oDUMQ5UiMHRDeiqUTrfh" +
            "F1PteXE8tyY4Z+jhzQNKuAuh4pif/uAAi2XIg0/nrvC0VRVewrTUwxxRTTzap/AJ0skGKK+D0SAAAAAElFTkSuQmCC";

    public User() {
        setAvatar(Base64.getDecoder().decode(getDefaultAvatar()));
    }

    public List<Meeting> getMyMeetings() {
        return myMeetings;
    }

    public void setMyMeetings(List<Meeting> myMeetings) {
        this.myMeetings = myMeetings;
    }

    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    public void setDefaultAvatar(String defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName()+ " "+getLastName();
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "} " + super.toString();
    }
}
