package com.order.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "orderItems",nullable = false)
    private int orderItems;

    @Column(name = "totalPrice",nullable = false)
    private double totalPrice;

    private LocalDateTime placeOrderTime;

    private double discount;

    private double payAmount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTOMER_ORDER", joinColumns = {
            @JoinColumn(name = "orderId", referencedColumnName = "orderId"),}, inverseJoinColumns = {
            @JoinColumn(name = "customerId", referencedColumnName = "customerId")})
    private Customer customer;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_ORDER", joinColumns = {
            @JoinColumn(name = "orderId", referencedColumnName = "orderId"),}, inverseJoinColumns = {
            @JoinColumn(name = "productId", referencedColumnName = "productId")})
    private List<Product> product= new ArrayList<>();

}
