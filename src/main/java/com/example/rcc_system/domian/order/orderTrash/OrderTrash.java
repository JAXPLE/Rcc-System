package com.example.rcc_system.domian.order.orderTrash;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="order_trash")
@Entity
public class OrderTrash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trashCode;
    private int orderCode;
    private int orderTypeCode;
    private int orderStack;


    public OrderTrash(OrderTrashRequestDto orderTrashRequestDto){
        this.trashCode = orderTrashRequestDto.getTrashCode();
        this.orderCode = orderTrashRequestDto.getOrderCode();
        this.orderTypeCode = orderTrashRequestDto.getOrderTypeCode();
        this.orderStack = orderTrashRequestDto.getOrderStack();

    }


    public OrderTrash(OrderTrash orderTrash){
        this.trashCode = orderTrash.getTrashCode();
        this.orderCode = orderTrash.getOrderCode();
        this.orderTypeCode = orderTrash.getOrderTypeCode();
        this.orderStack = orderTrash.getOrderStack();

    }


}
