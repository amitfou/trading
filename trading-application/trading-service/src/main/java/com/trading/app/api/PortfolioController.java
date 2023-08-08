package com.trading.app.api;

import com.trading.app.model.Holding;
import com.trading.app.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PortfolioController implements PortfolioApi {

  private final PortfolioService portfolioService;

  @Autowired
  PortfolioController(final PortfolioService portfolioService) {
    this.portfolioService = portfolioService;
  }


  @Override
  public List<Holding> getPortfolio() {
    return portfolioService.getPortfolio();
  }
}
