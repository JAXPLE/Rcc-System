package com.example.rcc_system.service;


import com.example.rcc_system.domian.order.Orders;
import com.example.rcc_system.domian.order.OrdersRepository;
import com.example.rcc_system.domian.order.order.Order;
import com.example.rcc_system.domian.order.order.OrderRepository;
import com.example.rcc_system.domian.user.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import com.example.rcc_system.domian.order.order.Order;
import com.example.rcc_system.domian.order.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrdersRepository orderRepository;
    private final ClientRepository clientRepository;

    public int addOrder(Orders order) {
        Orders savedOrder = orderRepository.save(order);
        System.out.println("서비스단 출력 오더코드: "+savedOrder.getOrderCode());
        System.out.println("서비스단 출력 주문자코드: "+savedOrder.getBuyerCode());
        System.out.println("서비스단 출력 주소: "+savedOrder.getOrderAddress());
        System.out.println("서비스단 출력 요청사항: "+savedOrder.getOrderRequest());
        System.out.println("서비스단 출력 시작날짜: "+savedOrder.getOrderState());


        return savedOrder.getOrderCode();
    }


    //마이페이지용 주문조회 구문
    public List<Orders> getOrderListByClientCode(int code) {

        return orderRepository.findOrdersByBuyerCode(code);
    }

    @Transactional
    public void updateOrderState(int orderCode){

        int clientCode = clientRepository.getClientCodeBytype();
        Orders order = orderRepository.getById(orderCode);
        if(order != null){
            order.setOrderState(order, clientCode);
        }
    }


    public Optional<Orders> findOrderByOrderCode(int code){
        return orderRepository.findById(code);
    }







}
