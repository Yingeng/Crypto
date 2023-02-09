package com.example.CryptoCurrency.controller;

import com.example.CryptoCurrency.controller.Enum.Frequency;
import com.example.CryptoCurrency.controller.Enum.Symbol;
import com.example.CryptoCurrency.model.GetKlineItemsRequest;
import com.example.CryptoCurrency.model.KlineResponseItem;
import com.example.CryptoCurrency.repo.KlineRepo;
import com.example.CryptoCurrency.service.ControllerValidationService;
import com.example.CryptoCurrency.service.KlineResponseRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kline")
public class KlineController {
    @Autowired
    private KlineResponseRetrievalService klineResponseRetrievalService;
    @Autowired
    private KlineRepo klineRepo;

    @Autowired
    private ControllerValidationService validator;

    @GetMapping
    public int load(@RequestParam String symbol, @RequestParam Long startTime, @RequestParam Long endTime) {
        List<KlineResponseItem> klineResponseItemList = klineResponseRetrievalService.getKlines(symbol, startTime, endTime);
        return klineRepo.insertAll(klineResponseItemList);
    }

    @GetMapping("/getKlineItems")
    @Cacheable(value = "KlineItems", key = "{#symbol, #startTime, #endTime, #frequency}")
    public List<KlineResponseItem> getKlineItems(@RequestParam String symbol, @RequestParam Long startTime,
                                                 @RequestParam Long endTime, @RequestParam String frequency) {
        GetKlineItemsRequest request = validator.validateGetKlineItems(symbol, startTime, endTime, frequency);
        System.out.println("hitting DB");

        //TODO validation, symbol(UTC,ETH,LTC), startTime > endTime, -1
        //TODO use ENUM for frequency(1min, 5min, 1day)
        // try catch(SymbolNotFoundException) https://www.baeldung.com/exception-handling-for-rest-with-spring(6.1)
        // calculate the performance difference (with redis and without redis) - write in the resume
        // TODO unit test and integration test - write into resume 90%
        //          https://www.baeldung.com/spring-boot-testing
        //          https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/integration-testing.html
        //          https://reflectoring.io/spring-boot-test/
        //          https://www.baeldung.com/junit-5   unit test

        return klineResponseRetrievalService.getKlinesByFrequency(request);
    }
}
