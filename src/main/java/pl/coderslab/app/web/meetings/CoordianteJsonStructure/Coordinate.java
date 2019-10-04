package pl.coderslab.app.web.meetings.CoordianteJsonStructure;

import java.util.List;

public class Coordinate {

    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public Double getLatitude() {
        return getResults().get(0).getGeometry().getLocation().getLat();
    }

    public Double getLongitude() {
        return getResults().get(0).getGeometry().getLocation().getLng();
    }
}
