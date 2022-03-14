package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantGroup;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface GroupMembersDao {

    //Find all members of a certain group by group id
    List<GroupMembers> findGroupMembersByGroupId(int groupId);

    //Add user to party
    boolean addToParty(User user, int groupId);

    //function to vote using user_vote, if else add vote to group restaurant list
    // true false if true add to vote if not -1

    public int createVote(GroupMembers groupMembers);

    //get list of restaurants for the group so each user can vote on
    List<RestaurantGroup> sendListOfRestaurants(int groupId);

//    GroupMembers vote(int member_id, int group_id, int user_vote);
    //restaurant

    int checkIfUserInGroupExists(String memberName, int groupId);



}
