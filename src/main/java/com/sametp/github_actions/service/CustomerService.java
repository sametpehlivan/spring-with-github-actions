package com.sametp.github_actions.service;


import com.sametp.github_actions.dto.CustomerRequest;
import com.sametp.github_actions.exception.NotFoundEntityException;
import com.sametp.github_actions.model.Customer;
import com.sametp.github_actions.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    @Transactional
    public String createCustomer(CustomerRequest request) {
        Customer customer = repository.save(request.to());
        return customer.getId();
    }
    @Transactional
    public String updateCustomer(CustomerRequest request) {
        Customer customer = request.to();
        if (!existsById(customer.getId()))
            throw new NotFoundEntityException();
        return  repository.save(customer).getId();
    }

    @Transactional
    public Customer deleteById(String id) {
        Customer customer = repository.findById(id).orElseThrow(NotFoundEntityException::new);
        repository.delete(customer);
        return customer;
    }
    public Customer findById(String id){
        return repository.findById(id).orElseThrow(NotFoundEntityException::new);
    }
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Boolean existsById(String id) {
        return repository.existsById(id);
    }
}
