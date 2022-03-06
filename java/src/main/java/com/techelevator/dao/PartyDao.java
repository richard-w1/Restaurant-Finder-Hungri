package com.techelevator.dao;

import com.techelevator.model.Party;
import com.techelevator.model.User;

import java.util.List;

public interface PartyDao {

    List<Party> findAll(String token);

    boolean create(Party party);

    //Do we need to retrieve a list of parties?
    //Do we need to CRUD parties?
}
