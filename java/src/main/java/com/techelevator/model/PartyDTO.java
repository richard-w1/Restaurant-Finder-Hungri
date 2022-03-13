package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @NotEmpty
    private String token;
    private String inviteLink;

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
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
//        LocalDateTime localdt = LocalDateTime.parse(date + " " + time, formatter);
//        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        endDate = java.sql.Timestamp.valueOf(date + " " + time + ":00");
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }
}
