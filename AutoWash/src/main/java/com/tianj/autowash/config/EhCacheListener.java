package com.tianj.autowash.config;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 缓存过期通知事件
 *
 * @author zhangxq
 * @version v1.0
 * @update 2019-01-21 11:13
 */

public class EhCacheListener extends CacheEventListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(EhCacheListener.class);

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        log.warn("-------------------------------- notifyElementExpired " + cache.getName() + " value: " + element);
    }
}
