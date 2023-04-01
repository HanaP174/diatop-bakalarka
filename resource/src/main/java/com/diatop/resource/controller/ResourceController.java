package com.diatop.resource.controller;

import com.diatop.model.order.OrderDto;
import com.diatop.resource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

  @Autowired
  private OrderService orderService;

    @PostMapping("/addOrder")
    @ResponseBody
    public void addUser(@RequestBody OrderDto orderDto) {
      orderService.addOrder(orderDto);
    }
}
