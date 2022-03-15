package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestaurantGroupDao implements RestaurantGroupDao {

    public RestaurantDao restaurantDao;
    public PartyDao partyDao;
    public PartyDTO partyDTO;
    public RestaurantGroupDao restaurantGroupDao;
    public GroupMembersDao groupMembersDao;
    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addDataToRestaurantGroup(Party party) {
        String sql = "insert into restaurant_group (group_id, restaurant_id)\n" +
                "select group_id, restaurant_id from groups\n" +
                "join restaurants ON restaurants.city = groups.location\n" +
                "where group_id = ? AND (restaurants.city = ? OR restaurants.zip_code = ?)";

        jdbcTemplate.update(sql, party.getId(), party.getLocation(), party.getLocation());

        return true;
    }

    @Override
    public boolean initSetTotalVotes(int groupId) {
        String sql = "UPDATE restaurant_group SET total_votes = 0 WHERE group_id = ?";
        jdbcTemplate.update(sql, groupId);

        return true;
    }

    @Override
    public List<RestaurantGroup> getRestaurantIdsByGroupId(int groupId) {
        List<RestaurantGroup> restaurantGroups = new ArrayList<>();
        String sql = "SELECT group_id, restaurant_id FROM restaurant_group WHERE group_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, groupId);
        while(result.next()) {
            restaurantGroups.add(mapRowToRestaurantGroup(result));
        }

        return restaurantGroups;
    }

//    @Override
//    public GroupMembers vote(int member_id, int group_id, int user_vote, int restaurantId) {
//        GroupMembers groupMember = new GroupMembers();
//
//        if(user_vote == 1) {
//            String sql = "update restaurant_group set total_votes = total_votes + 1 where group_id = ? and restaurant_id = ?";
//        } else if(user_vote == -1) {
//            String sql = "update restaurant_group set total_votes = total_votes - 1 where group_id = ? and restaurant_id = ?";
//        } else {
//            System.out.println("Invalid vote value.");
//        }
//    }

    @Override
    public boolean addMemberVotes(int groupId, List<GroupMembers> groupMembers) {
        //int validation = groupMembersDao.checkIfUserInGroupExists(groupMembers.getMember_id(), groupMembers.getGroup_id());

        //if (validation == 1) {
//            List<RestaurantGroup> restaurantList = new ArrayList<>();
//            restaurantList = restaurantGroupDao.getRestaurantIdsByGroupId(groupMembers.getGroup_id());


                for (GroupMembers groupMember: groupMembers) {
                    if (groupMember.getUserVote() == 1) {
                        String sql = "update restaurant_group set total_votes = total_votes + 1 where group_id = ? and restaurant_id = ?";
                        jdbcTemplate.update(sql, groupMember.getGroupId(), getRestaurantIdsByGroupId(groupId));
                    } else if (groupMember.getUserVote() == -1) {
                        String sql = "update restaurant_group set total_votes = total_votes - 1 where group_id = ? and restaurant_id = ?";
                        jdbcTemplate.update(sql, groupMember.getGroupId(), getRestaurantIdsByGroupId(groupId));
                    } else {
                        System.out.println("Invalid vote value.");
                    }
                }
                //Success
                return true;
//        } else {
//            System.out.println("You are not a member of this party.");
//            //Fail
//            return false;
//        }
    }

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
