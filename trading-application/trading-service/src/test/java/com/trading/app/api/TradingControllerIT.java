package com.trading.app.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.app.OpenApi;
import com.trading.app.entity.OrderEntity;
import com.trading.app.model.TradeOrder;
import com.trading.app.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.trading.app.service.TradeService.DEFAULT_USER_ID;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TradingControllerIT extends AbstractControllerIT {


  MockMvc mockMvc;

  @Autowired
  WebApplicationContext wac;

  @Autowired
  OrderRepository repository;

  @BeforeEach
  void beforeEach() {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.wac)
        .build();
  }

  @Autowired
  ObjectMapper mapper;

  @Test
  void allOrdersServiceRequest_shouldReturnHttpStatus200() throws Exception {
    mockMvc.perform(
            get(OpenApi.ALL_ORDERS)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",  hasSize(3)))
        .andExpect(jsonPath("$[0].id", is(220010)))
        .andExpect(jsonPath("$[0].ticker", is("APPL")))
        .andExpect(jsonPath("$[0].quantity", is(100)))
        .andExpect(jsonPath("$[0].price", is(120.4)))
        .andExpect(jsonPath("$[0].type", is("BUY")))
        .andExpect(jsonPath("$[0].status", is("EXECUTED")))
        .andExpect(jsonPath("$[1].id", is(220011)))
        .andExpect(jsonPath("$[1].ticker", is("APPL")))
        .andExpect(jsonPath("$[1].quantity", is(100)))
        .andExpect(jsonPath("$[1].price", is(128.1)))
        .andExpect(jsonPath("$[1].type", is("SELL")))
        .andExpect(jsonPath("$[1].status", is("EXECUTED")))
        .andExpect(jsonPath("$[2].id", is(220012)))
        .andExpect(jsonPath("$[2].ticker", is("MSFT")))
        .andExpect(jsonPath("$[2].quantity", is(100)))
        .andExpect(jsonPath("$[2].price", is(320.0)))
        .andExpect(jsonPath("$[2].type", is("SELL")))
        .andExpect(jsonPath("$[2].status", is("CANCELLED")));

  }

  @Test
  void executedServiceRequest_shouldReturnHttpStatus200() throws Exception {
    mockMvc.perform(
                    get(OpenApi.EXECUTED_ORDERS)
                            .characterEncoding(StandardCharsets.UTF_8.displayName())
                            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",  hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(220010)))
            .andExpect(jsonPath("$[0].ticker", is("APPL")))
            .andExpect(jsonPath("$[0].quantity", is(100)))
            .andExpect(jsonPath("$[0].price", is(120.4)))
            .andExpect(jsonPath("$[0].type", is("BUY")))
            .andExpect(jsonPath("$[0].status", is("EXECUTED")))
            .andExpect(jsonPath("$[1].id", is(220011)))
            .andExpect(jsonPath("$[1].ticker", is("APPL")))
            .andExpect(jsonPath("$[1].quantity", is(100)))
            .andExpect(jsonPath("$[1].price", is(128.1)))
            .andExpect(jsonPath("$[1].type", is("SELL")))
            .andExpect(jsonPath("$[1].status", is("EXECUTED")));
  }

  @Test
  void createOrder_shouldReturnHttpStatus200() throws Exception {
    TradeOrder order = new TradeOrder();
    order.setTicker("ACL");
    order.setPrice(BigDecimal.valueOf(200.0));
    order.setQuantity(100l);
    order.setType("BUY");
    String request = mapper.writeValueAsString(order);
    mockMvc.perform(
                    post(OpenApi.TRADE_URL)
                    .content(request)
                    .characterEncoding(StandardCharsets.UTF_8.displayName())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    List<OrderEntity> orders = repository.findByStatusAndUserId("CREATED", DEFAULT_USER_ID );
    assertThat(orders.size()).isEqualTo(1);
    assertThat(orders.get(0).getTicker()).isEqualTo("ACL");
    repository.deleteById(orders.get(0).getId());
  }

  @Test
  void createInvalidOrder_shouldReturnHttpStatus400() throws Exception {
    TradeOrder order = new TradeOrder();
    order.setTicker("ACL");
    order.setPrice(BigDecimal.valueOf(0));
    order.setQuantity(100l);
    order.setType("BUY");
    String request = mapper.writeValueAsString(order);
    mockMvc.perform(
                    post(OpenApi.TRADE_URL)
                            .content(request)
                            .characterEncoding(StandardCharsets.UTF_8.displayName())
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.price",  is("must be greater than 0")));

    List<OrderEntity> orders = repository.findByStatusAndUserId("CREATED", DEFAULT_USER_ID);
    assertThat(orders.size()).isEqualTo(0);
  }

}
