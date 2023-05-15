package com.example.rcc_system.service;


import com.example.rcc_system.domian.order.orderType.OrderType;
import com.example.rcc_system.domian.order.orderType.OrderTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderTypeService {

    @Autowired
    private final OrderTypeRepository orderTypeRepository;


    //주문 타입(일반/음식물/복합 등)을 전부 출력하는 메소드
    @Transactional
    public List<OrderType> getOrderTypeList() {

        return orderTypeRepository.findAll();

    }

}
