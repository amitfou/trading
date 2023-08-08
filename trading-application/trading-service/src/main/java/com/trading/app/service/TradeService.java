package com.trading.app.service;

import com.trading.app.entity.OrderEntity;
import com.trading.app.model.Order;
import com.trading.app.model.TradeOrder;
import com.trading.app.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TradeService {

  public static final long DEFAULT_USER_ID = 999l;
  private final OrderRepository orderRepository;

  @Autowired
  public TradeService(final OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void saveOrder(TradeOrder tradeOrder) {
    OrderEntity order = new OrderEntity();
    order.setOrderPrice(tradeOrder.getPrice());
    order.setType(tradeOrder.getType());
    order.setQuantity(tradeOrder.getQuantity());
    order.setTicker(tradeOrder.getTicker());
    order.setStatus("CREATED");
    order.setUserId(DEFAULT_USER_ID);
    orderRepository.save(order);
  }

  public List<Order> getOrders() {
    log.info("Finding all orders");
    final List<OrderEntity> foundOrders = orderRepository.findAllByUserId(DEFAULT_USER_ID);
    log.info("Found '{}' orders", foundOrders.size());
    return getOrderList(foundOrders);
  }

  public List<Order> getExecutedOrders() {
    log.info("Finding executed orders");
    final List<OrderEntity> foundOrders = orderRepository.findByStatusAndUserId("EXECUTED", DEFAULT_USER_ID);
    log.info("Found '{}' orders", foundOrders.size());
    return getOrderList(foundOrders);
  }

  private static List<Order> getOrderList(List<OrderEntity> foundOrders) {
    return foundOrders.stream()
            .map(orderEntity -> Order.builder()
                    .id(orderEntity.getId())
                    .price(orderEntity.getOrderPrice())
                    .quantity(orderEntity.getQuantity())
                    .ticker(orderEntity.getTicker())
                    .status(orderEntity.getStatus())
                    .type(orderEntity.getType())
                    .build())
            .toList();
  }

}
