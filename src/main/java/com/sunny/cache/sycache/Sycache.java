package com.sunny.cache.sycache;

import com.sunny.cache.sycache.store.DataStore;
import com.sunny.cache.sycache.store.StoreAccessException;
import com.sunny.cache.sycache.store.ValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 13:58 <br>
 * @see com.sunny.cache.sycache <br>
 */
public class Sycache<K,V> {
    private final DataStore<K, V> store;
    private static Logger logger = LoggerFactory.getLogger(Sycache.class);

    public Sycache(final DataStore<K, V> dataStore) {
        this.store = dataStore;
    }

    public V get(final K key) {
        try {
            ValueHolder<V> value = this.store.get(key);
            if (null == value) {
                return null;
            }
            return value.value();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
            return null;
        }
    }

    public void put(final K key, final V value) {
        try {
            this.store.put(key, value);
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
        }
    }

    public V remove(K key) {
        try {
            ValueHolder<V> value = this.store.remove(key);
            return value.value();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
            return null;
        }
    }

    public void clear() {
        try {
            this.store.clear();
        } catch (StoreAccessException e) {
            logger.error("store access error : ", e.getMessage());
            logger.error(e.getStackTrace().toString());
        }
    }
}
