package com.techelevator.dao;
import java.util.List;
import com.techelevator.model.GroupVotes;
import com.techelevator.model.User;

import javax.swing.*;

public interface GroupVotesDao {

    List<GroupVotes> retrieveVotes(int groupId);

//    int vote(User user);
}
