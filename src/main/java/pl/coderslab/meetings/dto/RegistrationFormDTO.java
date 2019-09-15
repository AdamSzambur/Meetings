package pl.coderslab.meetings.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;

public class RegistrationFormDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    private String password;

    @NotBlank
    @Size(min = 8, max = 12)
    private String rePassword;

    private CommonsMultipartFile avatar;

    private String firstName;

    private String lastName;


    public CommonsMultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(CommonsMultipartFile avatar) {
        this.avatar = avatar;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
