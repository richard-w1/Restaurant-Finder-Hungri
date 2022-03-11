package com.techelevator.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class GroupMembers {

        private int member_id;
        private String member_name;
        private int group_id;
        private int user_vote;


    public int getMember_id() {return member_id;}

    public void setMember_id(int member_id) {this.member_id = member_id;}

    public String getMember_name() {return member_name;}

    public void setMember_name(String member_name) {this.member_name = member_name;}



    public int getGroup_id() {return group_id;}

    public void setGroup_id(int group_id) {this.group_id = group_id;}

    public int getUser_vote() {
        return user_vote;
    }

    public void setUser_vote(int user_vote) {
        this.user_vote = user_vote;
    }
}
