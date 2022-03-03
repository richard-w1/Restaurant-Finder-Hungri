package com.techelevator.dao;

import com.techelevator.model.Party;
import com.techelevator.model.Restaurant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestaurantDao implements RestaurantDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> findRestaurant(String location) {
        List<Restaurant> restaurant = new ArrayList<>();
        String sql = "SELECT * FROM restaurants WHERE city LIKE ? OR zip_code LIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location, location);
        while(results.next()) {
            restaurant.add(mapRowToRestaurant(results));
        }
        return restaurant;

    }

    private Restaurant mapRowToRestaurant(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setDescription(rs.getString("description"));
        restaurant.setHoursOfOperation(rs.getString("hours_of_operation"));
        restaurant.setType(rs.getString("type"));
        restaurant.setOpen(rs.getBoolean("open"));
        restaurant.setPhoneNumber(rs.getBigDecimal("phone_number"));
        restaurant.setRating(rs.getInt("rating"));
        restaurant.setCity(rs.getString("city"));
        restaurant.setZipCode(rs.getInt("zip_code"));

        return restaurant;
    }

}
