package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private Double latitude;
    private Double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}