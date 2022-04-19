package com.example.backend.com.codegym.repository;

import com.example.backend.com.codegym.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends PagingAndSortingRepository<Country,Long> {
    Iterable<Country> findCountryByNameContaining(String name);
}
