package com.sunny.cache.sycache;


import com.sunny.cache.sycache.bean.User;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class SyCache107Test {

    @Test
    public void test01() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager manager = cachingProvider.getCacheManager();
        Cache<String, User> cache = manager.<String, User, Configuration<String, User>> createCache("Test", new MutableConfiguration<>());
        String key = "Sun";
        User user = new User();
        user.setName("Sunny");
        cache.put(key, user);
        System.out.println("Hello " + cache.get(key).getName());

    }

}
