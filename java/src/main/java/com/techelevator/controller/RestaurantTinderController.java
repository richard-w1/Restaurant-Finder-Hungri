package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.service.*;
import com.techelevator.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin("http://localhost:3000")
//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class RestaurantTinderController {
    private final Logger log = LoggerFactory.getLogger(RestaurantTinderController.class);

    PartyDao partyDao;
    GroupMembersDao groupMembersDao;
    RestaurantDao restaurantDao;
    RestaurantGroupDao restaurantGroupDao;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public RestaurantTinderController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
                                      UserDao userDao, PartyDao partyDao, GroupMembersDao groupMembersDao, RestaurantDao restaurantDao, RestaurantGroupDao restaurantGroupDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.partyDao = partyDao;
        this.groupMembersDao = groupMembersDao;
        this.restaurantDao = restaurantDao;
        this.restaurantGroupDao = restaurantGroupDao;
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

        int key = partyDao.create(party);
        party.setId(key);
        partyDao.setInviteLinkByGroupId(party.getId());
        restaurantGroupDao.addDataToRestaurantGroup(party);
        restaurantGroupDao.initSetTotalVotes(party.getId());
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
    @RequestMapping(value = "/group/{groupId}/view-finalists", method = RequestMethod.GET)
    public ResponseEntity<FindRestaurantGroupResponse> getGroupVotes(@PathVariable int groupId) {
        FindRestaurantGroupResponse findRestaurantGroupResponse = new FindRestaurantGroupResponse();
        findRestaurantGroupResponse.setRestaurantGroup(restaurantGroupDao.retrieveVotes(groupId));
        return new ResponseEntity<>(findRestaurantGroupResponse, null, HttpStatus.OK);
    }

    //Party invite link set to each groupId, when clicked add User into group as group member
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/invite", method = RequestMethod.POST)
    public void addGroupMember(@PathVariable int groupId, @RequestBody User user) {

        groupMembersDao.addToParty(user, groupId);
    }


    //Main party page
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    //Retrieve event details i.e. Event name, Host, Location, End Date.
    public ResponseEntity<FindPartyResponse> getEventDetails(@PathVariable int groupId) {
        FindPartyResponse findPartyResponse = new FindPartyResponse();
        findPartyResponse.setParty(partyDao.findPartyById(groupId));
        return new ResponseEntity<>(findPartyResponse, null, HttpStatus.OK);
    }
    //Retrieve restaurants in party location
    //** all part of main page **
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/restaurants", method = RequestMethod.GET)
    public ResponseEntity<FindRestaurantResponse> getPartyRestaurants(@PathVariable int groupId) {
        FindRestaurantResponse findRestaurantResponse = new FindRestaurantResponse();
        findRestaurantResponse.setRestaurants(restaurantDao.findRestaurant(partyDao.findPartyById(groupId).getLocation()));
        return new ResponseEntity<>(findRestaurantResponse, null, HttpStatus.OK);
    }
    //Retrieve party members
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/members", method = RequestMethod.GET)
    public ResponseEntity<FindGroupMembersResponse> getGroupMembers(@PathVariable int groupId) {
        FindGroupMembersResponse findGroupMembersResponse = new FindGroupMembersResponse();
        findGroupMembersResponse.setGroupMembers(groupMembersDao.findGroupMembersByGroupId(groupId));
        return new ResponseEntity<>(findGroupMembersResponse, null, HttpStatus.OK);
    }


    //Voting page for party, click button to open vote page
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/vote", method = RequestMethod.POST)
    public void addUserVotesToDatabase(@PathVariable int groupId, @RequestBody List<GroupMembers> groupMembers) {

        restaurantGroupDao.addMemberVotes(groupId, groupMembers);
    }

}