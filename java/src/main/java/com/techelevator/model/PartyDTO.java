package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class PartyDTO {


    @NotEmpty
    private int groupId;
    @NotEmpty
    private int hostId;
    @NotEmpty
    private String eventName;
    @NotEmpty
    private Timestamp endDate;
    @NotEmpty
    private boolean hasEnded;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }
}
