package com.techelevator.dao;

import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantDao {

    List<Restaurant> findRestaurant(String location);

    List<Restaurant> getRestaurantByGroupId(int groupId);
}