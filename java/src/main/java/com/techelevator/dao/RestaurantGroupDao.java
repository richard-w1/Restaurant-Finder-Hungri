package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface RestaurantGroupDao {

    boolean addDataToRestaurantGroup(Party party);

    List<Restaurant> sendListToGroupByGroupId(int groupId);

    List<RestaurantGroup> addMemberVotes(GroupMembers groupMembers);

    List<RestaurantGroup> retrieveVotes(int groupId);
}
