package com.trading.app.api;

import com.trading.app.model.Order;
import com.trading.app.model.TradeOrder;
import com.trading.app.OpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.trading.app.OpenApi.TRADING_API_TAG;

@Tag(name = TRADING_API_TAG)
@DefaultApiErrorResponses
public interface TradingApi {

  @PostMapping(value = OpenApi.TRADE_URL)
  @Operation(summary = "Place a trade order",
          description = "This API is for placing a trade order.")
  @ApiResponse(
          responseCode = "200",
          description = "The response payload contains the member's profile details.",
          content = @Content(schema = @Schema(implementation = TradeOrder.class)))
  void orderTrade(@RequestBody final TradeOrder trade);

  @GetMapping(value = OpenApi.ALL_ORDERS)
  @Operation(summary = "Retrieve the orders",
      description = "This API returns the order list.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the offer information.",
      content = @Content(schema = @Schema(implementation = Order.class)))
  List<Order> getOrders();

  @GetMapping(value = OpenApi.EXECUTED_ORDERS)
  @Operation(summary = "Retrieve the orders",
          description = "This API returns the order list.")
  @ApiResponse(
          responseCode = "200",
          description = "The response payload contains the offer information.",
          content = @Content(schema = @Schema(implementation = Order.class)))
  List<Order> getExecutedOrders();


}
