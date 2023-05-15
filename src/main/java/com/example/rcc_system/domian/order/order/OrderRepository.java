package com.example.rcc_system.domian.order.order;

import com.example.rcc_system.domian.order.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> getOrderByBuyerCode(Pageable pageable, int clientCode);

}
