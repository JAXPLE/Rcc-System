package com.example.rcc_system.domian.order.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderOwnListDto {
    private int clientCode;
    private int page;
    private List<Order> list;
    private int totalPage;
}
