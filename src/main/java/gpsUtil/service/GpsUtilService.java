package gpsUtil.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GpsUtilService {
    /**
     * Get all attractions as list
     * @return List of Attraction
     */
    List<Attraction> getAttractions();

    /**
     * Get current user location as a VisitedLocation for a given user
     * @param userId
     * @return Last known VisitedLocation for the given user
     */
    VisitedLocation getUserLocation(UUID userId);
}
