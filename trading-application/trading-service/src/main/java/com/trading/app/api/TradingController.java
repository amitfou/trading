package com.trading.app.api;

import com.trading.app.model.Order;
import com.trading.app.model.TradeOrder;
import com.trading.app.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TradingController implements TradingApi {

  private final TradeService tradeService;

  @Autowired
  TradingController(final TradeService tradeService) {
    this.tradeService = tradeService;
  }


  @Override
  public void orderTrade(@Valid TradeOrder trade) {
    tradeService.saveOrder(trade);
  }

  @Override
  public List<Order> getOrders() {
    final List<Order> foundOrders = tradeService.getOrders();
    if (foundOrders.isEmpty()) {
      throw new ResourceNotFoundException("No orders exists");
    }
    return foundOrders;
  }

  @Override
  public List<Order> getExecutedOrders() {
    final List<Order> foundOrders = tradeService.getExecutedOrders();
    if (foundOrders.isEmpty()) {
      throw new ResourceNotFoundException("No orders exists");
    }
    return foundOrders;
  }

}
