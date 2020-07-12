package com.mfo.creditservice.constraints;

import com.mfo.creditservice.domains.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestUserBlockedConstraint implements RequestConstraint {

    @Override
    public boolean checkAccept(Request request) {
        return request.getUser() != null && !request.getUser().isDisabled();
    }
}
