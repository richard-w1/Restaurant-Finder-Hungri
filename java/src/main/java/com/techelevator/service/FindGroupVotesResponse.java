package com.techelevator.service;

import com.techelevator.model.GroupVotes;

import java.util.List;

public class FindGroupVotesResponse {

    private List<GroupVotes> groupVotes;

    public List<GroupVotes> getGroupVotes() {
        return groupVotes;
    }

    public void setGroupVotes(List<GroupVotes> groupVotes) {
        this.groupVotes = groupVotes;
    }
}
