package com.example.CryptoCurrency.repo;

import com.example.CryptoCurrency.model.KlineResponseItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface KlineRepo {
    @Insert("INSERT INTO kline(symbol, openTime, openPrice, highPrice, lowPrice, closePrice, volume, " +
            "quoteAssetVolume, trades, buyBaseAssetVolume, buyQuoteAssetVolume, ignores, closeTime)" +
            "VALUES (#{symbol}, #{openTime}, #{openPrice}, #{highPrice}, #{lowPrice}, #{closePrice}," +
            "#{volume}, #{quoteAssetVolume}, #{trades}, #{buyBaseAssetVolume}, #{buyQuoteAssetVolume}," +
            "#{ignore}, #{closeTime})")
    int insert(KlineResponseItem klineResponseItem);

    @Insert({
            "<script>",
            "insert into kline(symbol, openTime, openPrice, highPrice, lowPrice, closePrice, volume, " +
                    "quoteAssetVolume, trades, buyBaseAssetVolume, buyQuoteAssetVolume, ignores, closeTime) values",
            "<foreach collection='klineResponseItems' item='k' separator=','>",
            "(#{k.symbol}, #{k.openTime}, #{k.openPrice}, #{k.highPrice}, #{k.lowPrice}, #{k.closePrice}," +
                    "#{k.volume}, #{k.quoteAssetVolume}, #{k.trades}, #{k.buyBaseAssetVolume}," +
                    "#{k.buyQuoteAssetVolume}, #{k.ignore}, #{k.closeTime})",
            "</foreach>",
            "</script>"
    })
    int insertAll(List<KlineResponseItem> klineResponseItems);

    @Select("select * from kline where openTime >= #{startTime} and closeTime <= #{endTime} and symbol = #{symbol}")
    List<KlineResponseItem> getKlineItem(Long startTime, Long endTime, String symbol);
}
