package com.example.rcc_system.controller;


import com.example.rcc_system.domian.order.Orders;
import com.example.rcc_system.domian.order.OrdersRequestDto;
import com.example.rcc_system.domian.order.order.Order;
import com.example.rcc_system.domian.order.order.OrderOwnListDto;
import com.example.rcc_system.domian.order.order.OrderRepository;
import com.example.rcc_system.domian.order.orderTrash.OrderTrash;
import com.example.rcc_system.domian.order.orderTrash.OrderTrashRepository;
import com.example.rcc_system.domian.order.orderTrash.OrderTrashRequestDto;
import com.example.rcc_system.domian.order.orderType.OrderType;
import com.example.rcc_system.domian.order.orderType.OrderTypeRepository;
import com.example.rcc_system.service.OrderService;
import com.example.rcc_system.service.OrderTypeService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    //    @Autowired
    private final OrderTypeService orderTypeService;
    private final OrderTypeRepository orderTypeRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderTrashRepository orderTrashRepository;

    //주문 타입(일반/음식물/복합 등)을 전부 출력하는 메소드. 오더 폼에서 사용 예정
    @GetMapping("/orderTypeList")
    public List<OrderType> getOrderTypeList() {
        List<OrderType> list = orderTypeService.getOrderTypeList();
        return list;
    }

    @PostMapping("addOrder")
    public ResponseEntity<Integer> addOrders(@RequestBody OrdersRequestDto ordersRequestDto) {
        Orders orders = new Orders(ordersRequestDto);
        int newOrderCode = orderService.addOrder(orders);
        System.out.println("컨트롤러단 출력 오더코드: "+newOrderCode);
        return new ResponseEntity<Integer>(newOrderCode, HttpStatus.CREATED);


    }

    @PostMapping("/getClientOrder")
    public OrderOwnListDto getOrderListByClientId(
            @RequestBody OrderOwnListDto orderOwnListDto,
            @PageableDefault(size = 8) Pageable pageable) {

        Page<Order> orderList = orderRepository.getOrderByBuyerCode(
                pageable.withPage(orderOwnListDto.getPage()),
                orderOwnListDto.getClientCode());

        orderOwnListDto.setList(orderList.getContent());
        orderOwnListDto.setTotalPage(orderList.getTotalPages());

        return orderOwnListDto;
    }

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder(
            @RequestParam int page,
            @PageableDefault(size = 10) Pageable pageable) {
        return orderRepository.findAll(pageable.withPage(page)).getContent();
    }

    @GetMapping("/getTotalPageSize")
    public int getAllOrderTotalSize(
            @PageableDefault(size = 10) Pageable pageable) {
        return orderRepository.findAll(pageable).getTotalPages();
    }

    @PostMapping("/updateOrderState")
    public void updateOrderState(@RequestParam("orderCode") int orderCode) {

        orderService.updateOrderState(orderCode);
    }
    @PostMapping("/addOrderTrash")
    public ResponseEntity<String> addOrderTrash(@RequestBody List<OrderTrash> requests) {

        List<OrderTrash> orderTrashes = new ArrayList<>();

        for (OrderTrash request : requests) {
            orderTrashes.add(new OrderTrash(request));
        }

        orderTrashRepository.saveAll(orderTrashes);

        return new ResponseEntity<>("생성이 완료되었습니다", HttpStatus.CREATED);



    }

}
