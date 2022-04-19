package com.example.backend.com.codegym.service;

import com.example.backend.com.codegym.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICountryService extends IGeneralService<Country> {
    Iterable<Country> findCountryByNameContaining(String name);
}
