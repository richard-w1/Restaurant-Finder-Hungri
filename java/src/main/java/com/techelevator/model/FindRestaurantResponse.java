package com.techelevator.model;

import java.util.List;

public class FindRestaurantResponse {

        private List<Restaurant> restaurant;

        public List<Restaurant> getRestaurants() {
            return restaurant;
        }

        public void setRestaurants(List<Restaurant> restaurant) {
            this.restaurant = restaurant;
        }
    }