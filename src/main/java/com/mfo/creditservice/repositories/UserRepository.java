package com.mfo.creditservice.repositories;

import com.mfo.creditservice.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
