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

}
