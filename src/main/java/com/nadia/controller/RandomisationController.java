package com.nadia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadia.service.RandomisationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yevhenii Filatov
 * @since 6/1/23
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/randomisation")
public class RandomisationController {
    private static final String UUID_URL = "/uuid";
    private static final String RANDOM_EXCHANGE_URL = "/exchange";
    private static final String CONCRETE_EXCHANGES_URL = "/exchange/{targetCurrency}";
    private static final ObjectMapper MARSHALLER = new ObjectMapper();

    private final RandomisationService randomisationService;

    @GetMapping
    public String showLinks() {
        return "randomisation";
    }

    @ResponseBody
    @GetMapping(value = UUID_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public String randomUuid() {
        return toJson(randomisationService.uuid());
    }

    @ResponseBody
    @GetMapping(value = RANDOM_EXCHANGE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public String randomExchange() {
        return toJson(randomisationService.randomExchange());
    }

    @ResponseBody
    @GetMapping(value = CONCRETE_EXCHANGES_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public String concreteExchange(@PathVariable RandomisationService.Currency targetCurrency) {
        return toJson(randomisationService.exchanges(targetCurrency));
    }

    @SneakyThrows
    private <T> String toJson(T object) {
        return MARSHALLER.writeValueAsString(object);
    }
}
