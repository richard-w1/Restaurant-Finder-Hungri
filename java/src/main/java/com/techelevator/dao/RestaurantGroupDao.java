package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface RestaurantGroupDao {

    boolean addDataToRestaurantGroup(Party party);

    List<RestaurantGroup> getRestaurantIdsByGroupId(int groupId);

//    GroupMembers vote(int member_id, int group_id, int user_vote, int restaurantId);

    int addMemberVotes(int groupId, GroupMembers groupMember);

    boolean initSetTotalVotes(int groupId);

    List<RestaurantGroup> retrieveVotes(int groupId);
}
