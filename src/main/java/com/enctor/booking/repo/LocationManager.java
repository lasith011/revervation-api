package com.enctor.booking.repo;

import com.enctor.booking.model.LocationInfo;

import java.math.BigDecimal;

/**
 * Singleton class which is keeping all the location information like distance, duration also prices. Since there is no
 * database, this will act like storage to keep track of all the location information.
 */
public class LocationManager {
    private static final LocationManager INSTANCE = new LocationManager();
    private final LocationInfo[][] locationInfos;

    private LocationManager() {
        this.locationInfos = new LocationInfo[LocationInfo.LOCATIONS.size()][LocationInfo.LOCATIONS.size()];
        this.initLocationInfo();
    }

    public static LocationManager getInstance() {
        return INSTANCE;
    }

    private void initLocationInfo() {
        this.locationInfos[0][0] = this.locationInfos[1][1] = this.locationInfos[2][2] = this.locationInfos[3][3] = new LocationInfo(BigDecimal.ZERO, 0.0D, 0);
        this.locationInfos[0][1] = this.locationInfos[1][0] = this.locationInfos[1][2] = this.locationInfos[2][1] = this.locationInfos[2][3] = this.locationInfos[3][2] = new LocationInfo(BigDecimal.valueOf(50), 30.0D, 60);
        this.locationInfos[0][2] = this.locationInfos[2][0] = this.locationInfos[1][3] = this.locationInfos[3][1] = new LocationInfo(BigDecimal.valueOf(100), 60.0D, 120);
        this.locationInfos[0][3] = this.locationInfos[3][0] = new LocationInfo(BigDecimal.valueOf(150), 90.0D, 180);
    }

    public LocationInfo getLocationInfo(String origin, String destination) {
        LocationInfo locationInfo = this.locationInfos[LocationInfo.LOCATIONS.indexOf(origin)][LocationInfo.LOCATIONS.indexOf(destination)];
        locationInfo.setOrigin(origin);
        locationInfo.setDestination(destination);
        return locationInfo;
    }
}

