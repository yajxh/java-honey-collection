package com.sunny.cache.sycache.jsr107;



import com.sunny.cache.sycache.Sycache;
import com.sunny.cache.sycache.store.impl.BasicDataStore;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 14:03 <br>
 * @see com.sunny.cache.sycache.store <br>
 */
public class SyCache107<K, V> implements Cache<K, V> {

	private Sycache<K, V> csCache = new Sycache<>(new BasicDataStore<K, V>());
	private volatile boolean isClosed;

	private String cacheName;

	private SyCache107Manager cacheManager;

	private Configuration<K, V> configuration;

	public SyCache107(SyCache107Manager cacheManager, String cacheName, Configuration<K, V> configuration) {
		this.cacheManager = cacheManager;
		this.cacheName = cacheName;
		this.configuration = configuration;
		this.isClosed = false;
	}

	@Override
	public void clear() {
		csCache.clear();
	}

	@Override
	synchronized public void close() {
		if (!this.isClosed) {
			this.isClosed = true;

			if (this.cacheManager != null) {
				this.cacheManager.releaseCache(this.cacheName);
			}

			this.csCache.clear();
		}
	}

	@Override
	public boolean containsKey(K arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public V get(K key) {
		return this.csCache.get(key);
	}

	@Override
	public Map<K, V> getAll(Set<? extends K> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getAndPut(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getAndRemove(K arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getAndReplace(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheManager getCacheManager() {
		return this.cacheManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends Configuration<K, V>> C getConfiguration(Class<C> arg0) {
		return (C) this.configuration;
	}

	@Override
	public String getName() {
		return this.cacheName;
	}

	@Override
	public <T> T invoke(K arg0, EntryProcessor<K, V, T> arg1, Object... arg2) throws EntryProcessorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> arg0, EntryProcessor<K, V, T> arg1,
			Object... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadAll(Set<? extends K> arg0, boolean arg1, CompletionListener arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(K key, V value) {
		this.csCache.put(key, value);

	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean putIfAbsent(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean remove(K key) {
		this.csCache.remove(key);
		return true;
	}

	@Override
	public boolean remove(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAll(Set<? extends K> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean replace(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean replace(K arg0, V arg1, V arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> clazz) {
		if (clazz.isAssignableFrom(this.csCache.getClass())) {
			return (T) this.csCache;
		}

		throw new IllegalArgumentException("Unwrapping to " + clazz + " is not " + "supported by this implementation");
	}

}