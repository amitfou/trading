package com.trading.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
        "averagePrice"
})
public class Holding {

    @Size(max = 20)
    private String ticker;

    @Size(max = 20)
    private Long quantity;

    @Size(max = 20)
    private BigDecimal averagePrice;
}
