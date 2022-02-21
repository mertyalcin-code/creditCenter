package com.mertyalcin.creditcenter.controller;

import com.mertyalcin.creditcenter.business.abstracts.CustomerService;
import com.mertyalcin.creditcenter.business.dto.CustomerDto;
import com.mertyalcin.creditcenter.business.requests.customerRequests.CreateCustomerRequest;
import com.mertyalcin.creditcenter.business.requests.customerRequests.UpdateCustomerRequest;
import com.mertyalcin.creditcenter.core.utils.results.DataResult;
import com.mertyalcin.creditcenter.core.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomersController {
    private CustomerService customerService;
    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/find-all")
    public DataResult<List<CustomerDto>> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/find-by-id/{id}")
    public DataResult<CustomerDto> findById(@PathVariable long id){
        return customerService.findById(id);
    }

    @PostMapping("/create")
    public Result create(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        System.out.println(createCustomerRequest.toString());
        return customerService.create(createCustomerRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        return customerService.update(updateCustomerRequest);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable long id){
        return customerService.delete(id);
    }

}
