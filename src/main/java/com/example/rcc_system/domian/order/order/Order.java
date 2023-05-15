package com.example.rcc_system.domian.order.order;

import com.example.rcc_system.domian.user.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="orders")
public class Order {

    @Id
    private int orderCode;
    private int buyerCode;
    private int sellerCode;
    private String orderAddress;
    private int orderTotalPrice;
    private String orderRequest;
    private LocalDateTime orderStartDate;
    private LocalDateTime orderEndDate;
    private String orderState;

    public Order(OrderRequestDto orderRequestDto){
        this.orderCode = orderRequestDto.getOrderCode();
        this.buyerCode = orderRequestDto.getBuyerCode();
        this.sellerCode = orderRequestDto.getSellerCode();
        this.orderAddress = orderRequestDto.getOrderAddress();
        this.orderTotalPrice = orderRequestDto.getOrderTotalPrice();
        this.orderRequest = orderRequestDto.getOrderRequest();
        this.orderStartDate = LocalDateTime.now();
        this.orderState = orderRequestDto.getOrderState();
    }

    public void setOrder(){
       this.orderState = "수거완료";
    }

}
