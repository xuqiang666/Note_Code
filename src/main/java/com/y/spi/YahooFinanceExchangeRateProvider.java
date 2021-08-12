package com.y.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 实现类
public class YahooFinanceExchangeRateProvider implements ExchangeRateProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public QuoteManager create() {
        logger.info("------> this is create <-------");
        return new YahooQuoteManagerImpl();
    }
}

