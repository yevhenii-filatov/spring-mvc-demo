package com.nadia.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Yevhenii Filatov
 * @since 6/1/23
 */

@Service
@RequiredArgsConstructor
public class RandomisationService {
    private List<Exchange> exchanges = List.of(
       new Exchange(Currency.USD, Currency.EUR, new BigDecimal("1.12")),
       new Exchange(Currency.USD, Currency.UAH, new BigDecimal("36.6")),
       new Exchange(Currency.EUR, Currency.USD, new BigDecimal("0.88")),
       new Exchange(Currency.EUR, Currency.UAH, new BigDecimal("38.2")),
       new Exchange(Currency.UAH, Currency.USD, new BigDecimal("0.027")),
       new Exchange(Currency.UAH, Currency.EUR, new BigDecimal("0.025"))
    );
    private static final Random RANDOM = new Random();

    public String uuid() {
        return UUID.randomUUID().toString();
    }

    public Exchange randomExchange() {
        return exchanges.get(randomNumber(0, exchanges.size()));
    }

    public List<Exchange> exchanges(Currency targetCurrency) {
        return exchanges.stream()
           .filter(exchange -> exchange.getSource().equals(targetCurrency))
           .toList();
    }

    private int randomNumber(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    @Data
    @AllArgsConstructor
    public static class Exchange {
        private Currency source;
        private Currency target;
        private BigDecimal rate;
    }

    public enum Currency {
        USD, EUR, UAH
    }
}
