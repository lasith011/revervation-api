package com.enctor.booking.repo;

import com.enctor.booking.model.LocationInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LocationManagerTest {
    public LocationManagerTest() {
    }

    @Test
    public void testGetLocationInfo() {
        String origin = "A";
        String destination = "D";
        LocationInfo locationInfo = LocationManager.getInstance().getLocationInfo(origin, destination);
        Assert.assertEquals("Origin should be A", origin, locationInfo.getOrigin());
        Assert.assertEquals("Destination should be D", destination, locationInfo.getDestination());
        Assert.assertEquals(90.0D, locationInfo.getDistance(), 0.0D);
        Assert.assertEquals(180.0D, locationInfo.getDuration().doubleValue(), 0.0D);
        Assert.assertEquals(BigDecimal.valueOf(150), locationInfo.getPrice());
    }
}
