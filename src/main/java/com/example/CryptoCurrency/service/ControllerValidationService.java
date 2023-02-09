package com.example.CryptoCurrency.service;

import com.example.CryptoCurrency.controller.Enum.Frequency;
import com.example.CryptoCurrency.controller.Enum.Symbol;
import com.example.CryptoCurrency.controller.Exception.InvalidStartAndEndTime;
import com.example.CryptoCurrency.controller.Exception.SymbolNotFoundException;
import com.example.CryptoCurrency.controller.Exception.UnsupportedFrequency;
import com.example.CryptoCurrency.model.GetKlineItemsRequest;
import org.springframework.stereotype.Service;

@Service
public class ControllerValidationService {

    public GetKlineItemsRequest validateGetKlineItems(String inputSymbolStr, Long startTime, Long endTime, String frequency) {

        Symbol s;
        try {
            s = Symbol.valueOf(inputSymbolStr);
        } catch (Exception ex) {
            throw new SymbolNotFoundException(String.format("Not supported symbol %s", inputSymbolStr), ex);
        }

        if (!isValidStartAndEndTime(startTime, endTime)) {
            throw new InvalidStartAndEndTime(String.format("Not validate input: startTime is %s, endTime is %s", startTime, endTime));
        }

        Frequency freq;
        try {
            freq = Frequency.valueOf(frequency);
        } catch (Exception ex) {
            throw new UnsupportedFrequency(String.format("Not supported frequency %s", frequency), ex);
        }

        GetKlineItemsRequest request = new GetKlineItemsRequest();
        request.setSymbol(s);
        request.setFrequency(freq);
        request.setStartTime(startTime);
        request.setEndTime(endTime);

        return request;
    }

    private boolean isValidStartAndEndTime(Long startTime, Long endTime) {
        return !(startTime >= endTime || startTime < 0 || endTime < 0);
    }
}
