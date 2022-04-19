package com.example.backend.com.codegym.controller;

import com.example.backend.com.codegym.model.City;
import com.example.backend.com.codegym.model.Country;
import com.example.backend.com.codegym.service.ICityService;
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
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping
    public ResponseEntity<Iterable<City>> findAllCity(@RequestParam(name = "q") Optional<String> q){
        Iterable<City> cities = cityService.findAll();
        if(q.isPresent()){
            cities = cityService.findCityByNameContaining(q.get());
        }
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findOneCity(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city){
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id, @RequestBody City newCity){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newCity.setId(id);
        return new ResponseEntity<>(cityService.save(newCity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.removeById(id);
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
    }
}
