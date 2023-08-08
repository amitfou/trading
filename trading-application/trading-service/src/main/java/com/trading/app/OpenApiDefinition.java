package com.trading.app;

import static lombok.AccessLevel.PRIVATE;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;

@OpenAPIDefinition(
    info = @Info(
        title = "Trading Service",
        description = OpenApiDefinition.DESCRIPTION),
    tags = {
        @Tag(name = OpenApi.TRADING_API_TAG)
    })
@NoArgsConstructor(access = PRIVATE)
public class OpenApiDefinition {

  static final String DESCRIPTION = """
      ## 
      ### Overview
                
      The service provides APIs that allow:

      * place trade order

      ### H2 Database
                                
      We have made the H2 console available whilst the application is running at:
                
      * <a href='/h2-trading-console' target='h2-trading-console'>/h2-trading-console</a>
            
      Connect to H2 using:
            
      * Driver Class:   **org.h2.Driver**
      * JDBC URL:       **jdbc:h2:mem:test**
      * User Name:      **guest**
      * Password:       **guest**

      """;
}
