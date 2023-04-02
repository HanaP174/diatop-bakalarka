package com.diatop.resource.service;

import com.diatop.model.order.Order;
import com.diatop.model.order.OrderDto;
import com.diatop.model.order.OrderRepository;
import com.diatop.model.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class OrderService {

  private final Logger logger = LoggerFactory.getLogger(OrderService.class);

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private EmailService emailService;

  public void addOrder(OrderDto orderDto) {
    Order order = mapOrderDtoToOrder(orderDto);
    if (order != null) {
      orderRepository.save(order).subscribe();
      emailService.sendEmail(order);
      logger.info("Order was saved");
    }
  }

  private Order mapOrderDtoToOrder(OrderDto orderDto) {
    if (orderDto.getUserId() == null) {
      logger.warn("Order wasn't created");
      return null;
    }

    Order order = new Order();
    order.setUserId(orderDto.getUserId());
    order.setAppointmentDate(orderDto.getAppointmentDate());
    order.setDeliveryDate(orderDto.getDeliveryDate());
    order.setStreet(orderDto.getStreet());
    order.setStreetNumber(orderDto.getStreetNumber());
    order.setCity(orderDto.getCity());
    order.setZipcode(orderDto.getZipcode());
    order.setNote(orderDto.getNote());
    order.setPersonalDelivery(orderDto.getPersonalDelivery());

    return order;
  }
}
