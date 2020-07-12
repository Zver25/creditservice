package com.mfo.creditservice.repositories;

import com.mfo.creditservice.domains.Request;
import com.mfo.creditservice.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {

    Page<Request> findByAcceptedTrue(Pageable page);

    Page<Request> findByAcceptedTrueAndUser(User user, Pageable page);

    List<Request> findAllByCreatedAtAfter(Date startAt);

}
