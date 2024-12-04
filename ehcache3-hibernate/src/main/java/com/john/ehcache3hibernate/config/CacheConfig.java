package com.john.ehcache3hibernate.config;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;
import java.util.Objects;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean(name="jcacheManager")
    public CacheManager ehCacheManager() throws URISyntaxException {
        CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());

        return cachingProvider.getCacheManager(
                Objects.requireNonNull(getClass().getResource("/ehcache.xml")).toURI(),
                getClass().getClassLoader()
        );
    }

    @Bean(name="ehcacheManager")
    public org.springframework.cache.CacheManager springCacheManager(CacheManager cacheManager) {
        return new org.springframework.cache.jcache.JCacheCacheManager(cacheManager);
    }
}
