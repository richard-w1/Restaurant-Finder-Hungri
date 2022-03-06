package com.techelevator.controller;

import com.techelevator.dao.GroupVotesDao;
import com.techelevator.dao.PartyDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import com.techelevator.service.FindGroupVotesResponse;
import com.techelevator.service.FindPartyResponse;
import com.techelevator.service.FindRestaurantResponse;
import com.techelevator.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
public class RestaurantTinderController {
    private final Logger log = LoggerFactory.getLogger(RestaurantTinderController.class);

    PartyDao partyDao;
    RestaurantDao restaurantDao;
    GroupVotesDao groupVotesDao;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public RestaurantTinderController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
                                      UserDao userDao, PartyDao partyDao, RestaurantDao restaurantDao, GroupVotesDao groupVotesDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.restaurantDao = restaurantDao;
        this.groupVotesDao = groupVotesDao;
}

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_group", method = RequestMethod.POST)
    public void createGroup(@Valid @RequestBody PartyDTO partyDTO) {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)tokenProvider.getAuthentication(partyDTO.getToken());

        Party party = new Party();
        party.setName(partyDTO.getEventName());
        party.setUserId(auth.getName());
        party.setEndDate(partyDTO.getEndDate());
        party.setHasEnded(partyDTO.isHasEnded());
        party.setLocation(partyDTO.getLocation());

        partyDao.create(party);
     }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/find_groups/{token}", method = RequestMethod.GET)
    public ResponseEntity<FindPartyResponse> getGroups(@PathVariable String token) {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)tokenProvider.getAuthentication(token);
        log.info("Find Groups for: " +auth.getName());
        FindPartyResponse findPartyResponse = new FindPartyResponse();
        findPartyResponse.setParties(partyDao.findAll(auth.getName()));
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