package com.trading.app.repository;

import com.trading.app.entity.HoldingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HoldingRepository extends CrudRepository<HoldingEntity, Long> {

  List<HoldingEntity> findAllByUserId(Long userId);

}
