package com.example.rcc_system.domian.order.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private int orderCode;
    private int buyerCode;
    private int sellerCode;
    private String orderAddress;
    private int orderTotalPrice;
    private String orderRequest;
    private LocalDateTime orderStartDate;
    private LocalDateTime orderEndDate;
    private String orderState;
}
