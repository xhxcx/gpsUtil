package gpsUtil.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import gpsUtil.service.GpsUtilService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GpsUtilControllerTests {

    @MockBean
    private GpsUtilService gpsUtilService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAttractionsShouldReturn200AndAttractionList() throws Exception {
        Attraction attraction = new Attraction("name", "city", "state", 10, 12);
        List<Attraction> attractionList = Collections.singletonList(attraction);

        Mockito.when(gpsUtilService.getAttractions()).thenReturn(attractionList);

        String expectedResponseJson = "[{\"longitude\":12.0,\"latitude\":10.0,\"attractionName\":\"name\",\"city\":\"city\",\"state\":\"state\",\"attractionId\":";

        mockMvc.perform(get("/getAttraction"))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedResponseJson));
                });
    }

    @Test
    public void getUserLocationShouldReturn200AndVisitedLocation() throws Exception {
        VisitedLocation visitedLocation = new VisitedLocation(UUID.fromString("00000000-0000-0001-0000-000000000002"), new Location(10,12), Date.from(Instant.parse("2021-10-18T10:15:30.00Z")));

        Mockito.when(gpsUtilService.getUserLocation(UUID.fromString("00000000-0000-0001-0000-000000000002"))).thenReturn(visitedLocation);

        String expectedResponseJson = "{\"userId\":\"00000000-0000-0001-0000-000000000002\",\"location\":{\"longitude\":12.0,\"latitude\":10.0},\"timeVisited\":\"2021-10-18T10:15:30.000+0000\"}";
        mockMvc.perform(get("/getUserLocation").param("userId", "00000000-0000-0001-0000-000000000002"))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().equalsIgnoreCase(expectedResponseJson));
                });
    }
}
