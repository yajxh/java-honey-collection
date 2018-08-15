package com.sunny.cache.sycache.store.impl;

import com.sunny.cache.sycache.store.DataStore;
import com.sunny.cache.sycache.store.StoreAccessException;
import com.sunny.cache.sycache.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <Description> BasicDataStore<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 15:52 <br>
 * @see com.sunny.cache.sycache.store.impl <br>
 */
public class BasicDataStore<K, V> implements DataStore<K, V> {

    private ConcurrentHashMap<K, ValueHolder<V>> map = new ConcurrentHashMap<>();

    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        return this.map.get(key);
    }
    @Override
    public PutStatus put(K key, V value) throws StoreAccessException {
        ValueHolder<V> v = new BasicValueHolder<V>(value);
        this.map.put(key, v);
        return PutStatus.PUT;
    }
    @Override
    public ValueHolder<V> remove(K key) throws StoreAccessException {
        return this.map.remove(key);
    }

    @Override
    public void clear() throws StoreAccessException {
        this.map.clear();
    }
}
