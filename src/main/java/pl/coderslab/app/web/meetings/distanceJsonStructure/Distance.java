package pl.coderslab.app.web.meetings.distanceJsonStructure;

import java.util.List;

public class Distance {
    private List<String> destination_addresses;
    private List<String> origin_addresses;

    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<String> getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(List<String> destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public List<String> getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(List<String> origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    // skrócimy sobie troche kod w samym serwisie

    public Long getDistanceInKm() {
        return (rows.get(0).getElements().get(0).getDistance().getValue())/1000;
    }

}
