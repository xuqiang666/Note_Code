package com.x.spi;

import java.time.LocalDate;
import java.util.List;

//  Step 2 : 这个是我们的业务类
public interface QuoteManager {
    List<String> getQuotes(String baseCurrency, LocalDate date);
}
