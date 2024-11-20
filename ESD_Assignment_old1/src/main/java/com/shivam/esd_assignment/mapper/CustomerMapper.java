package com.shivam.esd_assignment.mapper;

import com.shivam.esd_assignment.dto.CustomerRequest;
import com.shivam.esd_assignment.dto.CustomerResponse;
import com.shivam.esd_assignment.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}