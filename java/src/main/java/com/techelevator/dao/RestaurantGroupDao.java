package com.techelevator.dao;

import com.techelevator.model.Party;
import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantGroupDao {

    boolean addDataToRestaurantGroup(Party party);

    List<Restaurant> sendListToGroupByGroupId(int groupId);
}
