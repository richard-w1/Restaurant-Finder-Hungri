package com.techelevator.service;

import com.techelevator.model.GroupMembers;

import java.util.List;

public class FindGroupMembersResponse {

    private List<GroupMembers> groupMembers;

    public List<GroupMembers> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMembers> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
