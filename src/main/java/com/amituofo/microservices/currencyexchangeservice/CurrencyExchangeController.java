package com.amituofo.microservices.currencyexchangeservice;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {

  @Autowired
  private Environment _environment;

  @Autowired
  private ExchangeValueRepository _repository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    ExchangeValue exchangeValue = _repository.findByFromAndTo(from, to);
    int port = Integer.parseInt(_environment.getProperty("local.server.port"));
    exchangeValue.setPort(port);
    return exchangeValue;
  }
}
