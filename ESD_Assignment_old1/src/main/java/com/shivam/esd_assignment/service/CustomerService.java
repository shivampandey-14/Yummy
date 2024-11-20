package com.shivam.esd_assignment.service;

import com.shivam.esd_assignment.dto.CustomerRequest;
import com.shivam.esd_assignment.dto.CustomerResponse;
import com.shivam.esd_assignment.dto.LoginRequest;
import com.shivam.esd_assignment.entity.Customer;
import com.shivam.esd_assignment.exception.CustomerNotFoundException;
import com.shivam.esd_assignment.helper.EncryptionService;
import com.shivam.esd_assignment.helper.JWTHelper;
import com.shivam.esd_assignment.mapper.CustomerMapper;
import com.shivam.esd_assignment.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(@Valid LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }
}
