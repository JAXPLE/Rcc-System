package com.example.rcc_system.service;

import com.example.rcc_system.domian.order.orderTrash.OrderTrashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderTrashService {

    private final OrderTrashRepository orderTrashRepository;
}
