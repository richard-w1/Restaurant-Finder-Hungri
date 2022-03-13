package com.techelevator.service;

import com.techelevator.model.RestaurantGroup;

import java.util.List;

public class FindRestaurantGroupResponse {

    private List<RestaurantGroup> restaurantGroup;

    public List<RestaurantGroup> getRestaurantGroup() {
        return restaurantGroup;
    }

    public void setRestaurantGroup(List<RestaurantGroup> restaurantGroup) {
        this.restaurantGroup = restaurantGroup;
    }
}
