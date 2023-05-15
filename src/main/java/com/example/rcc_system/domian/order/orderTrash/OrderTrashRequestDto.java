package com.example.rcc_system.domian.order.orderTrash;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderTrashRequestDto {

    private int trashCode;
    private int orderCode;
    private int orderTypeCode;
    private int orderStack;



}
