package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.Party;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;

import java.util.List;

public interface PartyDao {

    List<Party> findAll(String token);

    int create(Party party);

    Party findPartyById(int groupId);

    List<Party> showUsersByGroupId(int Id);

    int getIdByUserId(String userId);
    //Do we need to retrieve a list of parties?
    //Do we need to CRUD parties?

    boolean setInviteLinkByGroupId(int groupId);

}
