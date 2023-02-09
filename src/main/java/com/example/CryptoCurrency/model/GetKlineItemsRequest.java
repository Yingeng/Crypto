package com.example.CryptoCurrency.model;

import com.example.CryptoCurrency.controller.Enum.Frequency;
import com.example.CryptoCurrency.controller.Enum.Symbol;
import lombok.Data;

@Data
public class GetKlineItemsRequest {
    private Symbol symbol;
    private Long startTime;
    private Long endTime;
    private Frequency frequency;
}


// java 14, record
