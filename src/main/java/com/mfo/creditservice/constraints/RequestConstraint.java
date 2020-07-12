package com.mfo.creditservice.constraints;

import com.mfo.creditservice.domains.Request;

public interface RequestConstraint {

    boolean checkAccept(Request request);
}
