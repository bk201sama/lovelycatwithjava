package org.eu.bk201sama.repository;

import org.eu.bk201sama.entity.KeyWord;
import org.eu.bk201sama.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KeyWordRepository extends CrudRepository<KeyWord, String> {

}
