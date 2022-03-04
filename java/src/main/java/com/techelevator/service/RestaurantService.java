package com.techelevator.service;
import com.techelevator.model.*;

import java.util.List;

public interface RestaurantService  {
    List<Restaurant> getRestaurants(String location);
}
