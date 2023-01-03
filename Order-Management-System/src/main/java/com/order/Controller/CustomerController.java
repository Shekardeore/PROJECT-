package com.order.Controller;

import com.order.Exceptions.CustomerNotFoundException;
import com.order.Exceptions.OrderNotFoundException;
import com.order.Model.Customer;
import com.order.Model.Payload.CustomerRequestPayload;
import com.order.Responce.CustomerResponse;
import com.order.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //create customer
    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestPayload requestPayload){
        Customer customer = customerService.createCustomer(requestPayload);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    // find customer
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) throws CustomerNotFoundException {

        Customer customer = customerService.findCustomerById(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //find customer with all orders
    @GetMapping("/customerWithAllOrders")
    public ResponseEntity<CustomerResponse> findCustomerAndOrderWithDisucount(@RequestParam Long customerId) throws CustomerNotFoundException, OrderNotFoundException {

        CustomerResponse customer = customerService.findCustomerAndOrder(customerId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
