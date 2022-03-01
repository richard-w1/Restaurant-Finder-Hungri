package com.techelevator.controller;

import com.techelevator.dao.PartyDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
public class RestaurantTinderController {

    PartyDao partyDao;
    RestaurantDao restaurantDao;

    public RestaurantTinderController(PartyDao partyDao, RestaurantDao restaurantDao) {
        this.partyDao = partyDao;
        this.restaurantDao = restaurantDao;
}

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_group", method = RequestMethod.POST)
    public void createGroup(@Valid @RequestBody PartyDTO partyDTO) {
        Party party = new Party();
        party.setName(partyDTO.getEventName());
        party.setHostId(partyDTO.getHostId());
        party.setEndDate(partyDTO.getEndDate());
        party.setHasEnded(partyDTO.isHasEnded());

        partyDao.create(party);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/find_groups", method = RequestMethod.POST)
    public ResponseEntity<FindPartyResponse> getGroups(@Valid @RequestBody FindPartyDTO findGroups) {
        FindPartyResponse findPartyResponse = new FindPartyResponse();
        findPartyResponse.setParties(partyDao.findAll(findGroups.getUserId()));
        return new ResponseEntity<FindPartyResponse>(findPartyResponse, null, HttpStatus.OK);
    }

}
