package com.springmongo.spring.rest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends MongoRepository<Item, String> {

}
