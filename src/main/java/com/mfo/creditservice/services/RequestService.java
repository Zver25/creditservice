package com.mfo.creditservice.services;

import com.mfo.creditservice.domains.Request;
import com.mfo.creditservice.domains.User;
import com.mfo.creditservice.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Page<Request> findAccepted(Pageable page) {
        return this.requestRepository.findByAcceptedTrue(page);
    }

    public Page<Request> findAcceptedByUser(User user, Pageable page) {
        return this.requestRepository.findByAcceptedTrueAndUser(user, page);
    }
}
