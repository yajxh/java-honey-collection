package com.sunny.cache.sycache.store.impl;

import com.sunny.cache.sycache.store.DataStore;
import com.sunny.cache.sycache.store.StoreAccessException;
import com.sunny.cache.sycache.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <Description> 弱引用方式实现存贮<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 16:06 <br>
 * @see com.sunny.cache.sycache.store.impl <br>
 */
public class WeakValueDataStore<K, V> implements DataStore<K, V> {
    private ConcurrentHashMap<K, ValueHolder<V>> map = new ConcurrentHashMap<K, ValueHolder<V>>();

    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        return this.map.get(key);
    }

    @Override
    public PutStatus put(K key, V value) throws StoreAccessException {
        ValueHolder<V> v = new WeakValueHolder<>(value);
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
