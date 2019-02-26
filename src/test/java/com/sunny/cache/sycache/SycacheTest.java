package com.sunny.cache.sycache;


import com.sunny.cache.sycache.bean.User;
import com.sunny.cache.sycache.store.impl.BasicDataStore;
import com.sunny.cache.sycache.store.impl.LRUDataStore;
import com.sunny.cache.sycache.store.impl.WeakValueDataStore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SycacheTest {

    @Test
    public void TestHelloWorld() {
        Sycache<String, String> cache = new Sycache<String, String>(new BasicDataStore<>());
        String key = "Hello";
        cache.put(key, "World!");
        System.out.println(key + " " + cache.get(key));
    }

    @Test
    public void TestWeakValue() throws InterruptedException {
        Sycache<String, User> cache = new Sycache<>(new WeakValueDataStore<>());
        String key = "sunny";
        User user = new User();
        user.setName("sunny");
        cache.put(key, user);
        //help gc
        user = null;
        System.out.println("Hello " + cache.get(key).getName());
        System.gc();
        //Thread.sleep(1000);
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Hello " + cache.get(key));
    }

    @Test
    public void TestLRU() {
        Sycache<String, User> cache = new Sycache<String, User>(new LRUDataStore<>(2));
        String key = "sunny";
        User user = new User();
        user.setName("sunny");

        String key1 = "yu";
        User user1 = new User();
        user1.setName("yu");

        String key2 = "rocket";
        User user2 = new User();
        user2.setName("rocket");

        cache.put(key, user);
        cache.put(key1, user1);
        cache.get(key);
        cache.put(key2, user2);

        if (cache.get(key) != null) {
            System.out.println("Hello " + cache.get(key).getName());
        }
        if (cache.get(key1) != null) {
            System.out.println("Hello " + cache.get(key1).getName());
        }
        if (cache.get(key2) != null) {
            System.out.println("Hello " + cache.get(key2).getName());
        }
    }

}
