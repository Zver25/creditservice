package com.mfo.creditservice.controllers;

import com.mfo.creditservice.domains.Config;
import com.mfo.creditservice.payloads.ResponsePayload;
import com.mfo.creditservice.payloads.UpdateConfigResponse;
import com.mfo.creditservice.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @PutMapping("{configName}")
    public ResponseEntity<ResponsePayload<String>> update(
            @PathVariable String configName,
            @RequestBody UpdateConfigResponse updateConfigResponse
    ) {
        ResponsePayload<String> payload = new ResponsePayload<>();
        Optional<Config> optionalConfig = configService.findByName(configName);
        if (optionalConfig.isPresent()) {
            Config config = optionalConfig.get();
            config.setValue(updateConfigResponse.getValue());
            Config updatedConfig = configService.save(config);
            payload.setDataPayload(updatedConfig.getValue());
        }
        else {
            payload.setErrorPayload("Config parameter not found");
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
