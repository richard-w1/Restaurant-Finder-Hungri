package com.techelevator.service;

import com.techelevator.model.GroupMembers;

import javax.swing.*;
import java.util.List;

public class FindGroupMembersResponse {

    private List<GroupMembers> groupMembers;
    private GroupMembers groupMember;

    public List<GroupMembers> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMembers> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public GroupMembers getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMembers groupMember) {
        this.groupMember = groupMember;
    }
}
