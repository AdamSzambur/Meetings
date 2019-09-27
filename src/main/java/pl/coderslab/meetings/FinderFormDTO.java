package pl.coderslab.meetings;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FinderFormDTO {
    private Double latitude;
    private Double longitude;
    private String findPhrase;
    private Integer distance;

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

    public String getFindPhrase() {
        return findPhrase;
    }

    public void setFindPhrase(String findPhrase) {
        this.findPhrase = findPhrase;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
