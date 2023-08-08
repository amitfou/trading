package com.trading.app.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.app.OpenApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PortfolioControllerIT extends AbstractControllerIT {


  MockMvc mockMvc;

  @Autowired
  WebApplicationContext wac;


  @BeforeEach
  void beforeEach() {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.wac)
        .build();
  }

  @Test
  void portfolioServiceRequest_shouldReturnHttpStatus200() throws Exception {
    mockMvc.perform(
            get(OpenApi.PORTFOLIO_URL)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",  hasSize(2)))
        .andExpect(jsonPath("$[0].ticker", is("MSFT")))
        .andExpect(jsonPath("$[0].quantity", is(100)))
        .andExpect(jsonPath("$[0].averagePrice", is(3.2)))
        .andExpect(jsonPath("$[1].ticker", is("APPL")))
        .andExpect(jsonPath("$[1].quantity", is(200)))
        .andExpect(jsonPath("$[1].averagePrice", is(1.2425)));
  }

}
