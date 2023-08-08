package com.trading.app;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class OpenApi {

  public static final String TRADING_API_TAG = "Trade APIs";
  public static final String TRADE_URL = "/orders";
  public static final String PORTFOLIO_URL = "/portfolio";
  public static final String ALL_ORDERS = "/orders";
  public static final String EXECUTED_ORDERS = "/orders/executed";

}
