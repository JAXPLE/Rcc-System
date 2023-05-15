package com.example.rcc_system.domian.order.orderType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderType")
public class OrderType {


    @Id
    int orderTypeCode;


    String orderTypeName;
    String orderTypeDetail;
    int orderTypePrice;


}
