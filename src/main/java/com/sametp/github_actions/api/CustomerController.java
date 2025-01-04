package com.sametp.github_actions.api;


import com.sametp.github_actions.dto.CustomerRequest;
import com.sametp.github_actions.dto.CustomerResponse;
import com.sametp.github_actions.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestBody @Valid CustomerRequest request
    ){
        return service.createCustomer(request);
    }
    @PutMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String update(
            @RequestBody @Valid CustomerRequest request
    ){
        return service.updateCustomer(request);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @PathVariable(value = "id",required = true) String id
    ){
        service.deleteById(id);
    }
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(
            @PathVariable(value = "id",required = true) String id
    ){
        return CustomerResponse.from(service.findById(id));
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAll(){
        return service.findAll().stream()
                .map(CustomerResponse::from)
                .toList();
    }
    @GetMapping("/exists/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Boolean existsById(
            @PathVariable(value = "id",required = true) String id
    ){
       return service.existsById(id);
    }


}
