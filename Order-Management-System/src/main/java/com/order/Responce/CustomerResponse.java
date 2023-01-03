package com.order.Responce;

import com.order.Model.Customer;
import com.order.Model.Order;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {

    private Customer customer;

    private List<Order> order;
}
