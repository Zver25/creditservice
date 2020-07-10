package com.mfo.creditservice.repositories;

import com.mfo.creditservice.domains.Config;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfigRepository extends CrudRepository<Config, Long> {

    Optional<Config> findByName(String name);

}
