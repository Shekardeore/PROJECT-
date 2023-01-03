package com.order.Service;

import com.order.Exceptions.CustomerNotFoundException;
import com.order.Exceptions.OrderNotFoundException;
import com.order.Model.Customer;
import com.order.Model.Order;
import com.order.Model.Payload.CustomerRequestPayload;
import com.order.Repository.CustomerRepository;
import com.order.Repository.OrderRepository;
import com.order.Responce.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    //create customer
    public Customer createCustomer(CustomerRequestPayload requestPayload){
        Customer customer = new Customer();

        customer.setName(requestPayload.getName());
        customer.setEmail(requestPayload.getEmail());
        customer.setMobileNumber(requestPayload.getMobileNumber());
        customer.setAddress(requestPayload.getAddress());

        return customerRepository.save(customer);
    }


    //find by customer id
    public Customer findCustomerById(Long id) throws CustomerNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(()->new CustomerNotFoundException("Customer Not found "+id));

    }

    //find customer and it's all order with discount
    public CustomerResponse findCustomerAndOrder(Long customerId) throws CustomerNotFoundException, OrderNotFoundException {
        CustomerResponse response = new CustomerResponse();

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with id "+customerId)));

        List<Order> orders = orderRepository.findByCustomerId(customer.get().getCustomerId());

        response.setCustomer(customer.get());
        response.setOrder(orders.stream().toList());
        return response;
    }

}
