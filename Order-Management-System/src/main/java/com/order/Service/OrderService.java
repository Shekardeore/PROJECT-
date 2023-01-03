package com.order.Service;

import com.order.Exceptions.OrderNotFoundException;
import com.order.Model.Customer;
import com.order.Model.Order;
import com.order.Model.Payload.OrderRequestPayload;
import com.order.Model.Product;
import com.order.Repository.CustomerRepository;
import com.order.Repository.OrderRepository;
import com.order.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    private static final int ORDER_FOR_GOLD_CATEGORY = 10;

    private static final int ORDER_FOR_PLATINUM_CATEGORY = 20;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    Timestamp timestamp = Timestamp.from(Instant.now());


    //create order and set discount
    @Transactional
    public Order createOrder(OrderRequestPayload requestPayload) {

        Customer customer = customerRepository.findById(requestPayload.getCustomerId()).get();

        List<Product> productList = productRepository.findAllById(requestPayload.getProductId());

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(productList);
        order.setOrderItems(requestPayload.getOrderItem());

        order.setPlaceOrderTime(LocalDateTime.from(timestamp.toLocalDateTime()));

        int count = requestPayload.getOrderItem();

        for (Product product:productList) {
            order.setTotalPrice(product.getPrice() * count);

            double totalProductPrice = product.getPrice() * count;

            if (count >= ORDER_FOR_GOLD_CATEGORY && count < ORDER_FOR_PLATINUM_CATEGORY) {
                order.setDiscount(totalProductPrice * 0.10);
            } else if (count >= ORDER_FOR_PLATINUM_CATEGORY) {
                order.setDiscount(totalProductPrice * 0.20);
            } else if (count == 9) {
                sendMail(customer);
            } else {
                order.setDiscount(0.0);
            }

            order.setPayAmount(totalProductPrice - order.getDiscount());
        }
        return orderRepository.save(order);
    }

    //send mail if order == 9
    private void sendMail(Customer customer) {
        String msg = customer.getName() + " You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold Customer and enjoy 10% discounts..!";
        System.out.println("Sent Mail To Customer = " + msg);
    }

    // find order by orderId
    public Order findOrderById(Long orderId) {
       return orderRepository.findById(orderId).orElseThrow(()->  new OrderNotFoundException("Order not found with id "+orderId));
    }

    //get all order
    public List<Order> orderList(){
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        Order order = findOrderById(id);
        if (order != null) {
            orderRepository.delete(order);
        } else {
            throw new OrderNotFoundException("Order not found");
        }

    }
}
