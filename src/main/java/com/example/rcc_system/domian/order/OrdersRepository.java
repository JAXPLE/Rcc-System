package com.example.rcc_system.domian.order;

import com.example.rcc_system.domian.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Native;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {





    List<Orders> findOrdersByBuyerCode(int BuyerCode);




}
