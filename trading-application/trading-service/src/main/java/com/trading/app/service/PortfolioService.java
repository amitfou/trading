package com.trading.app.service;

import com.trading.app.entity.HoldingEntity;
import com.trading.app.model.Holding;
import com.trading.app.repository.HoldingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trading.app.service.TradeService.DEFAULT_USER_ID;

@Slf4j
@Service
public class PortfolioService {

    @Autowired
    private HoldingRepository repository;

    public List<Holding> getPortfolio() {
        List<HoldingEntity> all = repository.findAllByUserId(DEFAULT_USER_ID);
        Collection<Holding> values = all.stream()
                .collect(Collectors.groupingBy(HoldingEntity::getTicker
                , Collectors.collectingAndThen(Collectors.toList(), list -> {
                            long total = list.stream().mapToLong(HoldingEntity::getQuantity).sum();
                            Optional<BigDecimal> totalPrice = list.stream().map(HoldingEntity::getPrice).reduce(BigDecimal::add);
                            return Holding.builder()
                                    .averagePrice(totalPrice.orElse(BigDecimal.ZERO).divide(BigDecimal.valueOf(total)))
                                    .quantity(total)
                                    .ticker(list.get(0).getTicker())
                                    .build();
                        }
                ))).values();

        return values.stream().toList();
    }
}
