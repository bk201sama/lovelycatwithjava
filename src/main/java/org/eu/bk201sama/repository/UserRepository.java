package org.eu.bk201sama.repository;

import org.eu.bk201sama.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
