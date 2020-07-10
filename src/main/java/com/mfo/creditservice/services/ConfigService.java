package com.mfo.creditservice.services;

import com.mfo.creditservice.domains.Config;
import com.mfo.creditservice.repositories.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigService {

    private ConfigRepository configRepository;

    public void setConfigRepository(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public Optional<Config> findByName(String name) {
        return configRepository.findByName(name);
    }

    public Config save(Config config) {
        return configRepository.save(config);
    }
}
