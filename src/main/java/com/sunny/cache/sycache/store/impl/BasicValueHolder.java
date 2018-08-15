package com.sunny.cache.sycache.store.impl;

import com.sunny.cache.sycache.store.ValueHolder;

/**
 * <Description> BasicValueHolder<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 15:54 <br>
 * @see com.sunny.cache.sycache.store.impl <br>
 */
public class BasicValueHolder<V> implements ValueHolder<V> {
    private final V refValue;

    public BasicValueHolder(final V value) {
        this.refValue = value;
    }

    @Override
    public V value() {
        return this.refValue;
    }
}
