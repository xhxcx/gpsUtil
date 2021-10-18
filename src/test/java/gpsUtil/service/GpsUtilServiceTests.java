package gpsUtil.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GpsUtilServiceTests {

    private final GpsUtil gpsUtilMock = Mockito.mock(GpsUtil.class);
    private final GpsUtilServiceImpl gpsUtilService = new GpsUtilServiceImpl(gpsUtilMock);

    @Test
    public void getAttractionsTest(){
        Attraction attraction = new Attraction("name", "city", "state", 10, 12);
        List<Attraction> attractionList = Collections.singletonList(attraction);

        Mockito.when(gpsUtilMock.getAttractions()).thenReturn(attractionList);

        List<Attraction> result = gpsUtilService.getAttractions();

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.containsAll(attractionList));
    }

    @Test
    public void getUserLocationTest(){
        VisitedLocation visitedLocation = new VisitedLocation(UUID.fromString("00000000-0000-0001-0000-000000000002"), new Location(10,12), Date.from(Instant.now()));

        Mockito.when(gpsUtilMock.getUserLocation(UUID.fromString("00000000-0000-0001-0000-000000000002"))).thenReturn(visitedLocation);

        Assert.assertEquals(visitedLocation, gpsUtilService.getUserLocation(UUID.fromString("00000000-0000-0001-0000-000000000002")));
    }
}
