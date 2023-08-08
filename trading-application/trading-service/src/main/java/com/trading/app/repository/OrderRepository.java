package com.trading.app.repository;

import com.trading.app.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {


  List<OrderEntity> findAllByUserId(Long userId);

  List<OrderEntity> findByStatusAndUserId(String value, Long userId);

}
