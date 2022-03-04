package com.techelevator.service;
import com.techelevator.model.Restaurant;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRestaurantService implements RestaurantService {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_IRL = "http://localhost:8081/find_restaurant";

    @Override
    public List<Restaurant> getRestaurants(String location) {
        List<Restaurant> restaurants = restTemplate.getForObject(API_IRL, List.class);
        return restaurants;
    }
}