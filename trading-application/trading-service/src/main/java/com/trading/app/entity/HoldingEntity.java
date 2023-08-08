package com.trading.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOLDING")
public class HoldingEntity {
    @Id
    @GeneratedValue(generator = "HOLDING_ID_SEQUENCE")
    @Column(name = "HOLDING_ID", updatable = false, nullable = false)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "STOCK_TICKER", nullable = false)
    private String ticker;

    @NotNull
    @Column(name = "PURCHASE_PRICE", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

}
