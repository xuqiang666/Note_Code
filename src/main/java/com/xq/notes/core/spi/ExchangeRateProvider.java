package com.xq.notes.core.spi;

// Step 1 : Provider 接口 , 我们用它返回一个通用的业务类
public interface ExchangeRateProvider {
    QuoteManager create();
}
