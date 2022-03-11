package com.techelevator.service;

import com.techelevator.model.Party;

import java.util.List;

public class FindPartyResponse {

    private List<Party> parties;

    private Party party;

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
