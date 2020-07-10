package com.mfo.creditservice.controllers;

import com.mfo.creditservice.domains.Country;
import com.mfo.creditservice.payloads.ResponsePayload;
import com.mfo.creditservice.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<ResponsePayload<List<Country>>> getList() {
        List<Country> countryList = new ArrayList<Country>();
        countryService.getAll().iterator().forEachRemaining(countryList::add);
        ResponsePayload<List<Country>> payload = new ResponsePayload<>();
        payload.setDataPayload(countryList);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("{countryId}")
    public ResponseEntity<ResponsePayload<Country>> getItem(
            @PathVariable Long countryId
    ) {
        ResponsePayload<Country> payload = new ResponsePayload<>();
        Optional<Country> optionalCountry = countryService.findById(countryId);
        if (optionalCountry.isPresent()) {
            payload.setDataPayload(optionalCountry.get());
        }
        else {
            payload.setErrorPayload("Country not found");
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<Country>> create(
            @RequestBody Country requestCountry
    ) {
        Country createCountry = new Country(requestCountry.getName());
        Country createdCountry = countryService.create(createCountry);
        ResponsePayload<Country> payload = new ResponsePayload<>();
        payload.setDataPayload(createdCountry);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
