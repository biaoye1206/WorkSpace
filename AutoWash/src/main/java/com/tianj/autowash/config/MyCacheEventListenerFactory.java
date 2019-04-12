package com.tianj.autowash.config;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author zhangxq
 * @version v1.0
 * @update 2019-01-21 13:45
 */
@Configuration
public class MyCacheEventListenerFactory extends CacheEventListenerFactory  {
    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new EhCacheListener();
    }
}
