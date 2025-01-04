package com.sametp.github_actions.repo;


import com.sametp.github_actions.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
