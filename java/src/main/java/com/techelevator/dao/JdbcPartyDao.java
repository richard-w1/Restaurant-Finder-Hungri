package com.techelevator.dao;

import com.techelevator.controller.RestaurantTinderController;
import com.techelevator.model.GroupMembers;
import com.techelevator.model.Party;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;



@Component
public class JdbcPartyDao implements PartyDao {
    private final Logger log = LoggerFactory.getLogger(JdbcPartyDao.class);

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    ServerProperties serverProperties;

    public JdbcPartyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Party> findAll(String username){
        log.info("findAll for: " + username);
        List<Party> party = new ArrayList<>();
        String sql = "SELECT * FROM groups WHERE user_id = ?";
        int userId = getIdByUserId(username);
        log.info("found user_id:" + userId);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            log.info("Found Group: " + results.getString("event_name"));
            party.add(mapRowToParty(results));
        }
        return party;
    }

    @Override
    public int create(Party party){
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "group_id";
        String insertGroup = "insert into groups (event_name,user_id,end_date,has_ended,location) values(?,?,?,?,?)";
        boolean groupCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertGroup, new String[]{id_column});
            ps.setString(1, party.getName());
            ps.setInt(2, getIdByUserId(party.getUserId()));
            ps.setTimestamp(3, party.getEndDate());
            ps.setBoolean(4, party.isHasEnded());
            ps.setString(5, party.getLocation());
            return ps;
        }
        , keyHolder) == 1;
        return keyHolder.getKey().intValue();
        //return groupCreated;
    }

    @Override
    public Party findPartyById(int groupId) {
        Party party = null;

        try {
            String sql = "SELECT * FROM groups WHERE group_id = ?";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, groupId);

            if(result.next()) {
                return mapRowToParty(result);
            }
        } catch (DataAccessException e) {
            System.out.println("Error Accessing Data.");
        }

        return party;
    }

    //    public boolean populateRestaurantGroupTable(){
//        return null;
//    }

    @Override
    public List<Party> showUsersByGroupId(int Id) {
            List<Party> groupMembersList = new ArrayList<>();
            String sql = "Select member_name, member_id, user_vote from group_members where group_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, Id);
            while (results.next()) {
                groupMembersList.add(mapRowToParty(results));
            }
            return groupMembersList;
        }

        @Override
        public int getIdByUserId(String userId){
            String sql = "SELECT * FROM users WHERE username = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if(results.next()){
                return results.getInt("user_id");
            }
            return 0;
        }

        @Override
        public boolean setInviteLinkByGroupId(int groupId) {
            String sql =
                    "update groups " +
                    "set invite_link = ? " +
                    "WHERE group_id = ?";

            jdbcTemplate.update(sql, "localhost:" + serverProperties.getPort() + "/group/" + groupId + "/invite", groupId);
            return true;
        }



        private Party mapRowToParty(SqlRowSet rs) {
            Party party = new Party();
            party.setId(rs.getInt("group_id"));
            party.setHostId(rs.getInt("user_id"));
            party.setName(rs.getString("event_name"));
            party.setEndDate(rs.getTimestamp("end_date"));
            party.setHasEnded(rs.getBoolean("has_ended"));
            party.setLocation(rs.getString("location"));
            party.setInviteLink(rs.getString("invite_link"));
            return party;
        }


}
