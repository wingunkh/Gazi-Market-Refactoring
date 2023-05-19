package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class Location {
    private long latitude;
    private long longitude;

    public Location(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

