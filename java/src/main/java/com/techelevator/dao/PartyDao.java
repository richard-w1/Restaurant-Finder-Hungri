package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.Party;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;

import java.util.List;

public interface PartyDao {

    List<Party> findAll(String token);

    boolean create(Party party);

    List<Party> showUsersByGroupId(int Id);

    List<Restaurant> sendRestaurantListByGroupId(int groupId);

    //Do we need to retrieve a list of parties?
    //Do we need to CRUD parties?
}
