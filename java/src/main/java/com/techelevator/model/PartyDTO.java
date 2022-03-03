package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class PartyDTO {
    private int groupId;
    private int hostId;
    @NotEmpty
    private String eventName;
    @NotEmpty
    private String date;
    @NotEmpty
    private String time;
    private Timestamp endDate;
    private boolean hasEnded;
    @NotEmpty
    private String location;

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
        endDate = java.sql.Timestamp.valueOf(date + " " + time);
        return endDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
