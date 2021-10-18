package gpsUtil.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import gpsUtil.service.GpsUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsUtilController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GpsUtilController.class);

    @Autowired
    private GpsUtilService gpsUtilService;

    /**
     * Endpoint to retrieve all attractions
     * @return ResponseEntity with List of Attraction as response content
     */
    @RequestMapping("/getAttraction")
    public ResponseEntity<List<Attraction>> getAttractions() {
        LOGGER.info("GET /getAttractions");
        return new ResponseEntity<>(gpsUtilService.getAttractions(), HttpStatus.OK);
    }

    /**
     *
     * Endpoint to retrieve last known location of the given user
     * @param userId as string
     * @return ResponseEntity with VisitedLocation as response content
     */
    @RequestMapping("/getUserLocation")
    public ResponseEntity<VisitedLocation> getUserLocation (@RequestParam String userId) {
        LOGGER.info("GET /getUserLocation userId = " + userId);
        return new ResponseEntity<>(gpsUtilService.getUserLocation(UUID.fromString(userId)), HttpStatus.OK);
    }
}
