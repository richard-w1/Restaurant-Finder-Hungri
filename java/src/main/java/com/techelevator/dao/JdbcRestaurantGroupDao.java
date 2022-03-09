package com.techelevator.dao;

import com.techelevator.model.Party;
import com.techelevator.model.PartyDTO;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestaurantGroupDao implements RestaurantGroupDao {

    public RestaurantDao restaurantDao;
    public PartyDao partyDao;
    public PartyDTO partyDTO;
    public RestaurantGroupDao restaurantGroupDao;
    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addDataToRestaurantGroup(Party party) {
        String sql = "insert into restaurant_group (group_id, restaurant_id)\n" +
                "select group_id, restaurant_id from groups\n" +
                "join restaurants ON restaurants.city = groups.location\n" +
                "where group_id = ? AND restaurants.city = ?";

        jdbcTemplate.update(sql, party.getId(), party.getLocation());

        return true;
    }

    @Override
    public List<Restaurant> sendListToGroupByGroupId(int groupId) {
        return null;
    }

}
