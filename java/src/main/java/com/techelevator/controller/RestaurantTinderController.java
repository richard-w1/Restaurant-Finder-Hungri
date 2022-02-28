package com.techelevator.controller;

import com.techelevator.dao.PartyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.*;
import com.techelevator.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/")
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

}
