package com.order.Controller;

import com.order.Exceptions.OrderNotFoundException;
import com.order.Model.Order;
import com.order.Model.Payload.OrderRequestPayload;
import com.order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //create order
    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestPayload requestPayload){
        Order order = orderService.createOrder(requestPayload);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //find order with discount
    @GetMapping("/findOrderById/{orderId}")
    public ResponseEntity<Order> findOrderAndDiscount(@PathVariable Long orderId) throws OrderNotFoundException {

        Order order = orderService.findOrderById(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // get all order
    @GetMapping("/getAllOrder")
    public ResponseEntity<List<Order>> findAllOrder(){
        List<Order> orderList = orderService.orderList();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/deleteOrderById/{orderId}")
    public String deleteOrderById(@PathVariable Long orderId) throws OrderNotFoundException {
         orderService.deleteOrder(orderId);
         return "Order Delete successfully...!";


    }
}
