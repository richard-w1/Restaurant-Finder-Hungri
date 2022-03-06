package com.techelevator.controller;

import com.techelevator.dao.GroupVotesDao;
import com.techelevator.dao.PartyDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.*;
import com.techelevator.service.FindGroupVotesResponse;
import com.techelevator.service.FindPartyResponse;
import com.techelevator.service.FindRestaurantResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
public class RestaurantTinderController {

    PartyDao partyDao;
    RestaurantDao restaurantDao;
    GroupVotesDao groupVotesDao;

    public RestaurantTinderController(PartyDao partyDao, RestaurantDao restaurantDao, GroupVotesDao groupVotesDao) {
        this.partyDao = partyDao;
        this.restaurantDao = restaurantDao;
        this.groupVotesDao = groupVotesDao;
}

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_group", method = RequestMethod.POST)
    public void createGroup(@Valid @RequestBody PartyDTO partyDTO) {
        Party party = new Party();
        party.setName(partyDTO.getEventName());
        party.setHostId(partyDTO.getHostId());
        party.setEndDate(partyDTO.getEndDate());
        party.setHasEnded(partyDTO.isHasEnded());
        party.setLocation(partyDTO.getLocation());

        partyDao.create(party);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/find_groups/{userId}", method = RequestMethod.GET)
    public ResponseEntity<FindPartyResponse> getGroups(@PathVariable int userId) {
        FindPartyResponse findPartyResponse = new FindPartyResponse();
        findPartyResponse.setParties(partyDao.findAll(userId));
        return new ResponseEntity<>(findPartyResponse, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/find_restaurants/{location}", method = RequestMethod.GET)
    public ResponseEntity<FindRestaurantResponse> getRestaurants(@PathVariable String location) {
        FindRestaurantResponse findRestaurantResponse = new FindRestaurantResponse();
        findRestaurantResponse.setRestaurants(restaurantDao.findRestaurant(location));
        return new ResponseEntity<>(findRestaurantResponse, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/party/{groupId}/view-finalists", method = RequestMethod.GET)
    public ResponseEntity<FindGroupVotesResponse> getGroupVotes(@PathVariable int groupId) {
        FindGroupVotesResponse findGroupVotesResponse = new FindGroupVotesResponse();
        findGroupVotesResponse.setGroupVotes(groupVotesDao.retrieveVotes(groupId));
        return new ResponseEntity<>(findGroupVotesResponse, null, HttpStatus.OK);
    }
}