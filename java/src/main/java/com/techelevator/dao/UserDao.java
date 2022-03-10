package com.techelevator.dao;

import com.techelevator.model.GroupMembers;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

//    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

//    boolean createAVote (int member_id, String member_name, String member_url, int group_id, int user_vote);

}
