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
@Table(name = "TRADE_ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue(generator = "TRADE_ORDER_ID_SEQUENCE")
    @Column(name = "ORDER_ID", updatable = false, nullable = false)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "STOCK_TICKER", nullable = false)
    private String ticker;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal orderPrice;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    private String type;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private String status;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private Long userId;


}
