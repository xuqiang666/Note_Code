package com.x.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

//  Step 3 : Provider 管理工具 , 加载出 Provider
/*
 ServiceLoader.LazyIterator
 核心就2个 ,一个是hasNext 中加载 resource , 再在 nextService 中实例化对应的server
这样的好处是 , 只有在正在使用的时候 , 才会真的去实例化这个对象 !!!
 */
public class ProviderManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Iterator<ExchangeRateProvider> providers(boolean refresh) {
        logger.info("------> [Step 1 : 进入 Provider 处理流程] <-------");
        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);

        if (refresh) {
            loader.reload();
        }
        Iterator<ExchangeRateProvider> provider = loader.iterator();


        return provider;

    }


}
