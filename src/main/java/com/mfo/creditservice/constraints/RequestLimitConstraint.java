package com.mfo.creditservice.constraints;

import com.mfo.creditservice.domains.Config;
import com.mfo.creditservice.domains.Request;
import com.mfo.creditservice.services.ConfigService;
import com.mfo.creditservice.services.RequestService;
import com.mfo.creditservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class RequestLimitConstraint implements RequestConstraint {

    private RequestService requestService;

    private ConfigService configService;

    private String limitRequestName;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Value("com.mfo.crerditservice.limitRequestName")
    public void setLimitRequestName(String limitRequestName) {
        this.limitRequestName = limitRequestName;
    }

    public boolean checkAccept(Request request) {
        int limit = 0;
        Optional<Config> optionalParam = configService.findByName(this.limitRequestName);
        if (optionalParam.isPresent()) {
            limit = Integer.parseInt(optionalParam.get().getValue());
        }
        Date minuteAgo = new Date();
        minuteAgo.setTime(minuteAgo.getTime() - 60_000);
        List<Request> requestList = requestService.findByCreatedAtAfter(minuteAgo);
        long count = requestList.stream().filter(requestItem ->
                (requestItem.getUser() != null && requestItem.getUser().getCountry() == request.getUser().getCountry())
        ).count();
        return count < limit;
    }
}
