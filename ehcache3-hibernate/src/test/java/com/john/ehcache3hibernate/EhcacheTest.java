package com.john.ehcache3hibernate;

import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.CacheManager;

import java.net.URL;

public class EhcacheTest {
    public static void main(String[] args) {
        try {
            URL configUrl = EhcacheTest.class.getResource("/ehcache.xml");
            if (configUrl == null) {
                throw new RuntimeException("ehcache.xml not found in classpath");
            }

            System.out.println("Loading Ehcache configuration from: " + configUrl);
            XmlConfiguration xmlConfig = new XmlConfiguration(configUrl);

            // Build the CacheManager
            CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
            cacheManager.init();

            System.out.println("Ehcache initialized successfully.");
            cacheManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


