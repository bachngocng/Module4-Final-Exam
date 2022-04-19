package com.example.backend.com.codegym.controller;

import com.example.backend.com.codegym.model.Country;
import com.example.backend.com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ResponseEntity<Iterable<Country>> findAll(@RequestParam(name = "q") Optional<String> q){
        Iterable<Country> countries = countryService.findAll();
        if(q.isPresent()){
            countries = countryService.findCountryByNameContaining(q.get());
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Country> save(@RequestBody Country country){
        return new ResponseEntity<>(countryService.save(country), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody Country newCountry){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newCountry.setId(id);
        return new ResponseEntity<>(countryService.save(newCountry), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.removeById(id);
        return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
    }
}