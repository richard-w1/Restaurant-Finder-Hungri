package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.RestaurantGroup;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupMembersDao implements GroupMembersDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupMembersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<GroupMembers> findGroupMembersByGroupId(int groupId) {
        List<GroupMembers> groupMembersList = new ArrayList<>();
        String sql = "Select * from group_members where group_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);
        while (results.next()) {
            groupMembersList.add(mapRowToGroupMembers(results));
        }
        return groupMembersList;
    }

    @Override
    public boolean addToParty(User user, int groupId) {
        int validation = checkIfUserInGroupExists(user.getUsername(), groupId);

        if(validation == 1) {
            System.out.println("User " + user.getUsername() + " is already in this group: " + groupId);
        } else {
            String id_column = "member_id";
            String sql = "INSERT INTO group_members (member_id, member_name, group_id, user_vote) VALUES (?, ?, ?, ?)";
            int added = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{id_column});
                ps.setInt(1, user.getId().intValue());
                ps.setString(2, user.getUsername());
                ps.setInt(3, groupId);
                ps.setInt(4, 0);
                return ps;
            });
        }
        return true;
    }

    @Override
    public int createVote(GroupMembers groupMembers) {
        return 0;
    }

    @Override
    public List<RestaurantGroup> sendListOfRestaurants(int groupId) {
        return null;
    }

//    @Override
//    public GroupMembers vote(int member_id, int group_id, int user_vote) {
//        GroupMembers groupMember = new GroupMembers();
//
//        if(user_vote == 1) {
//            String sql = "update restaurant_group set total_votes = total_votes + 1 where group_id = ? and restaurant_id = ?";
//        } else if(user_vote == -1) {
//
//        } else {
//            System.out.println("Invalid vote value.");
//        }
//    }

    @Override
    public int checkIfUserInGroupExists(String memberName, int groupId) {
        String sql = "SELECT * FROM group_members WHERE member_name = ? AND group_id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, memberName, groupId);

        if(result.next()){
            return 1;
        }
        else {
            return 0;
        }
    }


    private GroupMembers mapRowToGroupMembers(SqlRowSet rs) {
        GroupMembers groupMembers = new GroupMembers();
        groupMembers.setMember_id(rs.getInt("member_id"));
        groupMembers.setMember_name(rs.getString("member_name"));
        groupMembers.setGroup_id(rs.getInt("group_id"));
        groupMembers.setUser_vote(rs.getInt("user_vote"));

        return groupMembers;
    }

}