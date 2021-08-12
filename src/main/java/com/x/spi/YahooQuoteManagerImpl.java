package com.x.spi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class YahooQuoteManagerImpl implements QuoteManager {

    @Override
    public List<String> getQuotes(String baseCurrency, LocalDate date) {
        return new ArrayList<>();
    }
}

