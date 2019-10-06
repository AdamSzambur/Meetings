package pl.coderslab.app.web.meetings.DistanceJsonStructure;

public class Element {
    private DistanceOrDuration distance;
    private DistanceOrDuration duration;

    public DistanceOrDuration getDistance() {
        return distance;
    }

    public void setDistance(DistanceOrDuration distance) {
        this.distance = distance;
    }

    public DistanceOrDuration getDuration() {
        return duration;
    }

    public void setDuration(DistanceOrDuration duration) {
        this.duration = duration;
    }
}
