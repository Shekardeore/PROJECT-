package com.order.Repository;

import com.order.Model.Order;
import com.order.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o where o.customer.customerId = ?1")
    List<Order> findByCustomerId(Long customerId);

}
