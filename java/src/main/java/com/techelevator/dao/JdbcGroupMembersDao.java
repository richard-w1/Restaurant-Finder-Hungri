package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupMembersDao implements GroupMembersDao{

        private final JdbcTemplate jdbcTemplate;

        public JdbcGroupMembersDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


//        public List<Restaurant> findRestaurant(String location) {
//            List<Restaurant> restaurant = new ArrayList<>();
//            String sql = "SELECT * FROM restaurants WHERE city LIKE ? OR zip_code LIKE ?";
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location, location);
//            while(results.next()) {
//                restaurant.add(mapRowToRestaurant(results));
//            }
//            return restaurant;
//
//        }

        private GroupMembers mapRowToGroupMembers(SqlRowSet rs) {
            GroupMembers groupMembers = new GroupMembers();
            groupMembers.setMember_id(rs.getInt("member_id"));
            groupMembers.setMember_name(rs.getString("member_name"));
            groupMembers.setMember_url(rs.getString("member_url"));
            groupMembers.setGroup_id(rs.getInt("group_id"));

            return groupMembers;
        }

    }

    //}
