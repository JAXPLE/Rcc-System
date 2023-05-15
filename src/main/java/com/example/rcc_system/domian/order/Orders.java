package com.example.rcc_system.domian.order;


import com.example.rcc_system.domian.board.Board;
import com.example.rcc_system.domian.board.BoardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;

    private int buyerCode;
    @Nullable
    private int sellerCode;
    private String orderAddress;

    private int orderTotalPrice;
    @Nullable
    private String orderRequest;

    @CreatedDate
    private LocalDateTime orderStartDate;
    @Nullable
    private LocalDateTime orderEndDate;
    @Nullable
    private String orderState;


    public Orders(OrdersRequestDto ordersRequestDto) {
        this.orderCode = ordersRequestDto.getOrderCode();
        this.buyerCode = ordersRequestDto.getBuyerCode();
        this.sellerCode = ordersRequestDto.getSellerCode();
        this.orderAddress = ordersRequestDto.getOrderAddress();
        this.orderTotalPrice = ordersRequestDto.getOrderTotalPrice();
        this.orderRequest = ordersRequestDto.getOrderRequest();
        this.orderStartDate = ordersRequestDto.getOrderStartDate();
        this.orderEndDate = ordersRequestDto.getOrderEndDate();
        this.orderState = ordersRequestDto.getOrderState();

    }

    public void setOrderState(Orders orders, int clientCode) {

        this.sellerCode = clientCode;
        //시간 수정 구문 넣어야 함
        this.orderEndDate = LocalDateTime.now();

        this.orderState = "수거완료";

    }





}
