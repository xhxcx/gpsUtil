package gpsUtil.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GpsUtilService {
    List<Attraction> getAttractions();
    VisitedLocation getUserLocation(UUID userId);
}
