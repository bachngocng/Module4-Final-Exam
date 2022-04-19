package com.example.backend.com.codegym.service;

import com.example.backend.com.codegym.model.Country;
import com.example.backend.com.codegym.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void removeById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Iterable<Country> findCountryByNameContaining(String name) {
        return countryRepository.findCountryByNameContaining(name);
    }
}
