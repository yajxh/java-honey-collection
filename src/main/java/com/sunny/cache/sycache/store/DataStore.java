package com.sunny.cache.sycache.store;

/**
 * <Description> 数据存储接口<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 14:02 <br>
 * @see com.sunny.cache.sycache.store <br>
 */
public interface DataStore<K, V> {

    /**
     * 获取缓存 <br>
     * @param key
     * @return
     * @throws StoreAccessException
     */
    ValueHolder<V> get(K key) throws StoreAccessException;

    /**
     *
     * @param key
     * @param value
     * @return
     * @throws StoreAccessException
     */
    PutStatus put(K key, V value) throws StoreAccessException;

    /**
     * 移除缓存
     * @param key
     * @return
     * @throws StoreAccessException
     */
    ValueHolder<V> remove(K key) throws StoreAccessException;

    /**
     * 清空缓存
     * @throws StoreAccessException
     */
    void clear() throws StoreAccessException;


    enum PutStatus {
        /**
         * New value was put
         */
        PUT,
        /**
         * New value was put and replace old value
         */
        UPDATE,
        /**
         * New value was dropped
         */
        NOOP
    }
}
