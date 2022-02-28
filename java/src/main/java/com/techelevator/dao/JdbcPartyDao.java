package com.techelevator.dao;

import com.techelevator.model.Party;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;



@Component
public class JdbcPartyDao implements PartyDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPartyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Party> findAll(long userId){
            List<Party> party = new ArrayList<>();
            String sql = "SELECT * FROM group WHERE user_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()) {
                party.add(mapRowToParty(results));
            }
            return party;
    }

    public boolean create(Party party){
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "group_id";
        String insertGroup = "insert into group (group_name,user_id,end_date,has_ended) values(?,?,?,?)";
        boolean groupCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertGroup, new String[]{id_column});
            ps.setString(1, party.getName());
            ps.setInt(2, party.getHostId());
            ps.setTimestamp(3, party.getEndDate());
            ps.setBoolean(3, party.isHasEnded());
            return ps;
        }
        , keyHolder) == 1;
        return groupCreated;
    }

    private Party mapRowToParty(SqlRowSet rs) {
        Party party = new Party();
        party.setId(rs.getInt("group_id"));
        party.setName(rs.getString("group_name"));
        party.setHostId(rs.getInt("user_id"));
        party.setEndDate(rs.getTimestamp("end_date"));
        party.setHasEnded(rs.getBoolean("has_ended"));
        return party;
    }


}
