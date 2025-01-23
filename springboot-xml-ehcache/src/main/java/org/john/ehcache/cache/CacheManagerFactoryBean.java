package org.john.ehcache.cache;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


public class CacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {

//    private static Logger log = LoggerFactory.getLogger(CacheManagerFactoryBean.class);

    private String xmlPath;

    private boolean shared;

    private CacheManager cacheManager;

    @Override
    public void afterPropertiesSet() throws IOException, URISyntaxException {
        CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        if (cacheManager == null) {
            this.cacheManager = cachingProvider.getCacheManager(
                    getClass().getResource(xmlPath).toURI(),
                    getClass().getClassLoader()
            );
        } else if (!shared) {
            this.cacheManager = cachingProvider.getCacheManager(
                    getClass().getResource(xmlPath).toURI(),
                    getClass().getClassLoader()
            );
        }
    }

    @Override
    public void destroy() {
        if (this.cacheManager != null) {
            this.cacheManager.close();
        }
    }

    @Override
    public CacheManager getObject() {
        return this.cacheManager;
    }

    @Override
    public java.lang.Class<? extends CacheManager>	getObjectType() {
        return (this.cacheManager != null? this.cacheManager.getClass(): CacheManager.class);
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public boolean isSingleton() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

}