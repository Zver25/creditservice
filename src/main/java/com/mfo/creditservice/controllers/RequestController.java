package com.mfo.creditservice.controllers;

import com.mfo.creditservice.domains.Request;
import com.mfo.creditservice.domains.User;
import com.mfo.creditservice.payloads.PaginatedResponsePayload;
import com.mfo.creditservice.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    private Pageable preparePageable(HttpServletRequest request) {
        int page;
        int perPage;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        }
        catch (NumberFormatException e) {
            page = 1;
        }
        try {
            perPage = Integer.parseInt(request.getParameter("perPage"));
        }
        catch (NumberFormatException e) {
            perPage = 100;
        }
        return PageRequest.of(page - 1, perPage);
    }

    @GetMapping("accepted")
    public ResponseEntity<PaginatedResponsePayload<Request>> getAccepted(HttpServletRequest request) {
        Pageable pageable = preparePageable(request);
        Page<Request> requestPage = requestService.findAccepted(pageable);
        PaginatedResponsePayload<Request> payload = new PaginatedResponsePayload<>();
        payload.setDataPayload(
                requestPage.toList(),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                requestPage.getTotalElements()
        );
        return ResponseEntity.ok(payload);
    }

    @GetMapping("accepted/user/{user}")
    public ResponseEntity<PaginatedResponsePayload<Request>> getAcceptedByUser(
            @PathVariable User user,
            HttpServletRequest request
    ) {
        Pageable pageable = preparePageable(request);
        Page<Request> requestPage = requestService.findAcceptedByUser(user, pageable);
        PaginatedResponsePayload<Request> payload = new PaginatedResponsePayload<>();
        payload.setDataPayload(
                requestPage.toList(),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                requestPage.getTotalElements()
        );
        return ResponseEntity.ok(payload);
    }
}
