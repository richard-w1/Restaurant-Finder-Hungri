package com.techelevator.dao;

import com.techelevator.model.GroupVotes;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupVotesDao implements GroupVotesDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupVotesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GroupVotes> retrieveVotes(int groupId) {
        List<GroupVotes> groupVotes = new ArrayList<>();
        String sql =
                "SELECT group_votes.group_id, group_votes.restaurant_id, group_votes.total_votes, groups.event_name " +
                "FROM group_votes " +
                "INNER JOIN groups ON group_votes.group_id = groups.group_id " +
                "WHERE groups.group_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);
        while(results.next()) {
            groupVotes.add(mapRowToGroupVotes(results));
        }
        return groupVotes;
    }


    private GroupVotes mapRowToGroupVotes(SqlRowSet result) {
        GroupVotes groupVotes = new GroupVotes();

        groupVotes.setGroupId(result.getInt("group_id"));
        groupVotes.setRestaurantId(result.getInt("restaurant_id"));
        groupVotes.setVotes(result.getInt("total_votes"));

        return groupVotes;
    }
}
