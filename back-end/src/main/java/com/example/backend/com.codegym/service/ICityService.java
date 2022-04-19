package com.example.backend.com.codegym.service;

import com.example.backend.com.codegym.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City>{
    Iterable<City> findCityByNameContaining(String name);
}
