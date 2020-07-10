package com.mfo.creditservice.controllers;

import com.mfo.creditservice.domains.Country;
import com.mfo.creditservice.domains.User;
import com.mfo.creditservice.payloads.ResponsePayload;
import com.mfo.creditservice.payloads.UserCreateRequest;
import com.mfo.creditservice.services.CountryService;
import com.mfo.creditservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private CountryService countryService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<ResponsePayload<List<User>>> getList() {
        List<User> userList = new ArrayList<>();
        userService.findAll().iterator().forEachRemaining(userList::add);
        ResponsePayload<List<User>> payload = new ResponsePayload<>();
        payload.setDataPayload(userList);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<ResponsePayload<User>> getItem(
            @PathVariable Long userId
    ) {
        ResponsePayload<User> payload = new ResponsePayload<>();
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            payload.setDataPayload(optionalUser.get());
        }
        else {
            payload.setErrorPayload("User not found");
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<User>> create(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        ResponsePayload<User> payload = new ResponsePayload<>();
        Optional<Country> optionalCountry = countryService.findById(userCreateRequest.getCountryId());
        if (!optionalCountry.isPresent()) {
            payload.setErrorPayload("Country not found");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        }
        User user = new User();
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());
        user.setCountry(optionalCountry.get());
        User createdUser = userService.create(user);
        payload.setDataPayload(createdUser);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
