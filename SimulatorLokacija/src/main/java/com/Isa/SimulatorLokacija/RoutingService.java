package com.Isa.SimulatorLokacija;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutingService {

    @Autowired
    private RestTemplate restTemplate;

    public List<LocationBean> getPointsBetween(LocationBean start, LocationBean end) {
        List<LocationBean> pointsBetween = new ArrayList<>();

        // Poziv OSM API-ja i dobijanje rute
        List<LocationBean> routePoints = getRouteFromOSM(start, end);

        // Dodavanje svih tačaka rute u listu pointsBetween
        pointsBetween.addAll(routePoints);

        return pointsBetween;
    }

    // Metoda koja poziva OSM API i dobija rute između dve tačke
    private List<LocationBean> getRouteFromOSM(LocationBean start, LocationBean end) {
        String url = "http://router.project-osrm.org/route/v1/driving/" +
                start.getLongitude() + "," + start.getLatitude() + ";" +
                end.getLongitude() + "," + end.getLatitude() + "?overview=false&steps=true";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonResponse = responseEntity.getBody();

        List<LocationBean> points = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            JsonNode stepsNode = rootNode.path("routes").get(0).path("legs").get(0).path("steps");
            for (JsonNode stepNode : stepsNode) {
                JsonNode intersectionsNode = stepNode.path("intersections");
                for (JsonNode intersectionNode : intersectionsNode) {
                    JsonNode locationNode = intersectionNode.path("location");
                    double longitude = locationNode.get(0).asDouble();
                    double latitude = locationNode.get(1).asDouble();
                    points.add(new LocationBean(longitude, latitude));
                }
            }
        } catch (IOException e) {
            // Uhvatamo izuzetak ako se pojavi i ispisujemo ga
            e.printStackTrace();
            // Možemo vratiti null ili praznu listu u ovom slučaju
            // points = null;
            points = new ArrayList<>(); // ili points = Collections.emptyList();
        }

        return points;
    }
}


