package com.sunny.cache.sycache.store.impl;

import com.sunny.cache.sycache.store.ValueHolder;

import java.lang.ref.WeakReference;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 16:05 <br>
 * @see com.sunny.cache.sycache.store.impl <br>
 */
public class WeakValueHolder<V> implements ValueHolder {
    private WeakReference<V> v;

    public WeakValueHolder(V value) {
        super();
        if (null == value) {
            return;
        }
        this.v = new WeakReference<>(value);
    }

    /**
     * 使用JDk提供的WeakReference 建立对象的弱引用，在没有强引用存在时，若执行GC，jvm将回收对象，调用WeakReference.get时将返回null
     */
    @Override
    public V value() {
        return this.v.get();
    }
}
