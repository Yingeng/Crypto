package com.example.CryptoCurrency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@RedisHash("KlineResponseItem")
public class KlineResponseItem implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;
    @Id
    private String symbol;
    @Id
    private Long openTime;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal closePrice;
    private BigDecimal volume;
    @Id
    private Long closeTime;
    private BigDecimal quoteAssetVolume;
    private Long trades;
    private BigDecimal buyBaseAssetVolume;
    private BigDecimal buyQuoteAssetVolume;
    private BigDecimal ignore;

    public static KlineResponseItem buildFromArray(List<Object> fields, String symbol) {
        KlineResponseItem klineResponseItem = new KlineResponseItem();
        int index = 0;
        klineResponseItem.setSymbol(symbol).setOpenTime(Long.valueOf(fields.get(index++).toString())).setOpenPrice(new BigDecimal(fields.get(index++).toString()))
                .setHighPrice(new BigDecimal(fields.get(index++).toString())).setLowPrice(new BigDecimal(fields.get(index++).toString()))
                .setClosePrice(new BigDecimal(fields.get(index++).toString())).setVolume(new BigDecimal(fields.get(index++).toString()))
                .setCloseTime(Long.valueOf(fields.get(index++).toString())).setQuoteAssetVolume(new BigDecimal(fields.get(index++).toString()))
                .setTrades(Long.valueOf(fields.get(index++).toString())).setBuyBaseAssetVolume(new BigDecimal(fields.get(index++).toString()))
                .setBuyQuoteAssetVolume(new BigDecimal(fields.get(index++).toString())).setIgnore(new BigDecimal(fields.get(index).toString()));
        return klineResponseItem;
    }

    public String simplePrint() {
        String info = symbol + "\n" + openTime + "\n" + openPrice + "\n" + highPrice + "\n" + lowPrice + "\n" + closePrice
                + "\n" + volume + "\n" + closeTime + "\n" + quoteAssetVolume + "\n" + trades + "\n" + buyBaseAssetVolume
                + "\n" + buyQuoteAssetVolume + "\n" + ignore;
        return info;
    }
}
