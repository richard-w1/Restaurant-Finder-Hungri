package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.Restaurant;

import java.util.List;

public interface GroupMembersDao {

    //Find all members of a certain group by group id
    List<GroupMembers> findByGroupId(int groupId);

    //function to vote using user_vote, if else add vote to group restaurant list
    // true false if true add to vote if not -1

    public GroupMembers createVote(int member_id, String member_name, String member_url, int group_id, int user_vote);

    //get list of restaurants for the group so each user can vote on
    //restaurant

    public List<Restaurant> sendListToGroupByGroupId(int groupId);


}
