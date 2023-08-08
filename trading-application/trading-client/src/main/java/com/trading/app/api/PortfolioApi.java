package com.trading.app.api;

import com.trading.app.model.Holding;
import com.trading.app.OpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.trading.app.OpenApi.TRADING_API_TAG;

@Tag(name = TRADING_API_TAG)
@DefaultApiErrorResponses
public interface PortfolioApi {


  @GetMapping(value = OpenApi.PORTFOLIO_URL)
  @Operation(summary = "Retrieve the portfolio",
      description = "This API returns the portfolio list.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the offer information.",
      content = @Content(schema = @Schema(implementation = Holding.class)))
  List<Holding> getPortfolio();


}
