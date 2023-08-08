package com.trading.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
        "type",
        "status"
})
public class Order {

  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @Size(max = 4, message="Ticker should be max 4 character in length")
  private String ticker;

  @Min(value = 1, message = "Quantity should be at least 1")
  private Long quantity;

  @PositiveOrZero
  private BigDecimal price;

  private String type;

  private String status;
}
