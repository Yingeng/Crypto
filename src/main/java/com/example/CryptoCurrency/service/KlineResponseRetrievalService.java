package com.example.CryptoCurrency.service;

import com.example.CryptoCurrency.model.GetKlineItemsRequest;
import com.example.CryptoCurrency.model.KlineResponseItem;
import com.example.CryptoCurrency.repo.KlineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KlineResponseRetrievalService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${urlTemplate}")
    String baseUrl;
    @Value("${limit}")
    Integer limit;

    @Autowired
    private KlineRepo klineRepo;

    public List<KlineResponseItem> getKlines(String symbol, Long startTime, Long endTime) {
        List<KlineResponseItem> klineResponseItemList = new ArrayList<>();
        Long period = Long.valueOf(limit * 60 * 1000);

        for (Long start = startTime; start < endTime; start += period) {
            String url = String.format(baseUrl, symbol, start, Math.min(start + period, endTime), limit);

            ResponseEntity<List<List<Object>>> responseEntity = restTemplate.exchange
                    (url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

            List<List<Object>> response = responseEntity.getBody();

            klineResponseItemList.addAll(response.stream().map(res -> KlineResponseItem.buildFromArray(res, symbol))
                    .collect(Collectors.toList()));
        }

        return klineResponseItemList;
    }

    public List<KlineResponseItem> getKlinesByFrequency(@NotNull GetKlineItemsRequest request) {

        List<KlineResponseItem> klineResponseItemList = klineRepo.getKlineItem(request.getStartTime(),
                request.getEndTime(), request.getSymbol().toString());
        List<KlineResponseItem> res = new ArrayList<>();

        for (int i = 0; i < klineResponseItemList.size(); i += request.getFrequency().getValue()) {
            int start = i + 1;
            KlineResponseItem beginItem = klineResponseItemList.get(i);
            while (start < i + request.getFrequency().getValue() && start < klineResponseItemList.size()) {
                KlineResponseItem cur = klineResponseItemList.get(start);
                beginItem.setVolume(beginItem.getVolume().add(cur.getVolume()));
                beginItem.setQuoteAssetVolume(beginItem.getQuoteAssetVolume().add(cur.getQuoteAssetVolume()));
                beginItem.setTrades(beginItem.getTrades() + cur.getTrades());
                beginItem.setBuyQuoteAssetVolume(beginItem.getBuyQuoteAssetVolume().add(cur.getBuyQuoteAssetVolume()));
                beginItem.setBuyBaseAssetVolume(beginItem.getBuyBaseAssetVolume().add(cur.getBuyBaseAssetVolume()));
                beginItem.setHighPrice(beginItem.getHighPrice().max(cur.getHighPrice()));
                beginItem.setLowPrice(beginItem.getLowPrice().min(cur.getLowPrice()));
                beginItem.setClosePrice(cur.getClosePrice());
                beginItem.setCloseTime(cur.getCloseTime());
                start++;
            }
            res.add(beginItem);
        }
        return res;
    }

}
