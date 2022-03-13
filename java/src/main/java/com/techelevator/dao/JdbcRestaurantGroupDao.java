package com.techelevator.dao;

import com.techelevator.model.*;
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

//    @Override
//    public List<>

    @Override
    public List<RestaurantGroup> retrieveVotes(int groupId) {
        List<RestaurantGroup> restaurantGroup = new ArrayList<>();
        String sql =
                "SELECT restaurant_group.group_id, restaurant_group.restaurant_id, restaurant_group.total_votes, groups.event_name " +
                        "FROM restaurant_group " +
                        "INNER JOIN groups ON restaurant_group.group_id = groups.group_id " +
                        "WHERE groups.group_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);
        while(results.next()) {
            restaurantGroup.add(mapRowToRestaurantGroup(results));
        }
        return restaurantGroup;
    }


    private RestaurantGroup mapRowToRestaurantGroup(SqlRowSet result) {
        RestaurantGroup restaurantGroup = new RestaurantGroup();

        restaurantGroup.setGroupId(result.getInt("group_id"));
        restaurantGroup.setRestaurantId(result.getInt("restaurant_id"));
        restaurantGroup.setTotalVotes(result.getInt("total_votes"));

        return restaurantGroup;
    }

}
