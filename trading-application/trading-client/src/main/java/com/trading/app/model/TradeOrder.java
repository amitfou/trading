package com.trading.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "id",
    "ticker",
    "quantity",
    "price",
    "type"
})
public class TradeOrder {

  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @Size(max = 5, message="Ticker should be max 5 character in length")
  private String ticker;

  @Min(value = 1, message = "Quantity should be at least 1")
  private Long quantity;

  @Positive
  private BigDecimal price;

  @NotEmpty(message = "Type must not be null or empty")
  @Pattern(regexp="BUY|SELL")
  private String type;


}
