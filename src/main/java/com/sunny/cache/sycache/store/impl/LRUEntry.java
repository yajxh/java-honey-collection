package com.sunny.cache.sycache.store.impl;

import com.sunny.cache.sycache.store.ValueHolder;

import java.util.Map;

/**
 * <Description> LRU Cache存储<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 15:56 <br>
 * @see com.sunny.cache.sycache.store.impl <br>
 */
public class LRUEntry<K,V extends ValueHolder<?>> implements Map.Entry<K,ValueHolder<?>> {
    /**
     * non-null
     */
    final K key;

    /**
     * non-null
     */
    ValueHolder<?> v;

    /**
     * 双向链表
     */
    private LRUEntry<K, ValueHolder<?>> pre;
    private LRUEntry<K, ValueHolder<?>> next;

    public LRUEntry(K key, V value) {
        super();

        this.key = key;
        this.v = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public ValueHolder<?> getValue() {
        return this.v;
    }

    @Override
    public ValueHolder<?> setValue(ValueHolder<?> value) {
        ValueHolder<?> oldValue = this.v;
        this.v = value;
        return oldValue;
    }

    public LRUEntry<K, ValueHolder<?>> getPre() {
        return pre;
    }

    public void setPre(LRUEntry<K, ValueHolder<?>> pre) {
        this.pre = pre;
    }

    public LRUEntry<K, ValueHolder<?>> getNext() {
        return next;
    }

    public void setNext(LRUEntry<K, ValueHolder<?>> next) {
        this.next = next;
    }
}
