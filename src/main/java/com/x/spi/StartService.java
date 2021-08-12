package com.x.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class StartService{

    private static Logger logger = LoggerFactory.getLogger(StartService.class);

    public static void main(String[] args) {
        logger.info("------> [App 中获取]  <-------");

        ProviderManager providerManager = new ProviderManager();

        Iterator<ExchangeRateProvider> providers = providerManager.providers(true);

        while (providers.hasNext()) {
            logger.info("------> [providers 获取完成 :{}] <-------", providers.next().create());
        }
    }
}

