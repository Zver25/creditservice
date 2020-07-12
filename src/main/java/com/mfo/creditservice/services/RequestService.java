package com.mfo.creditservice.services;

import com.mfo.creditservice.constraints.RequestConstraint;
import com.mfo.creditservice.domains.Request;
import com.mfo.creditservice.domains.User;
import com.mfo.creditservice.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RequestService {

    private RequestRepository requestRepository;
    private Set<RequestConstraint> constraints;

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Autowired
    public void setConstraints(Set<RequestConstraint> constraints) {
        this.constraints = constraints;
    }

    public Page<Request> findAccepted(Pageable page) {
        return this.requestRepository.findByAcceptedTrue(page);
    }

    public Page<Request> findAcceptedByUser(User user, Pageable page) {
        return this.requestRepository.findByAcceptedTrueAndUser(user, page);
    }

    public Request create(Request request) {
        boolean isAccept = this.constraints.stream().allMatch(constraint -> constraint.checkAccept(request));
        request.setAccepted(isAccept);
        return this.requestRepository.save(request);
    }

    public List<Request> findByCreatedAtAfter(Date date) {
        return this.requestRepository.findAllByCreatedAtAfter(date);
    }
}
