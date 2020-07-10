package com.mfo.creditservice.repositories;

import com.mfo.creditservice.domains.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
