package com.sunny.cache.sycache.jsr107;

import javax.cache.CacheManager;
import javax.cache.configuration.OptionalFeature;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 14:03 <br>
 * @see com.sunny.cache.sycache.store <br>
 */
public class SyCaching107Provider implements CachingProvider {

    private static final String DEFAULT_URI_STRING = "urn:X-cscache:jsr107-default-config";

    private static final URI URI_DEFAULT;

    private final Map<ClassLoader, ConcurrentMap<URI, CacheManager>> cacheManagers = new WeakHashMap<>();

    static {
        try {
            URI_DEFAULT = new URI(DEFAULT_URI_STRING);
        } catch (URISyntaxException e) {
            throw new javax.cache.CacheException(e);
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void close(ClassLoader arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void close(URI arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public CacheManager getCacheManager() {
        return this.getCacheManager(this.getDefaultURI(), this.getDefaultClassLoader(), null);
    }

    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
        return this.getCacheManager(uri, classLoader, getDefaultProperties());
    }

    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader, Properties properties) {
        uri = uri == null ? getDefaultURI() : uri;
        classLoader = classLoader == null ? getDefaultClassLoader() : classLoader;
        properties = properties == null ? new Properties() : cloneProperties(properties);

        ConcurrentMap<URI, CacheManager> cacheManagersByURI = this.cacheManagers.get(classLoader);

        if (cacheManagersByURI == null) {
            cacheManagersByURI = new ConcurrentHashMap<>();
        }

        CacheManager cacheManager = cacheManagersByURI.get(uri);

        if (cacheManager == null) {
            cacheManager = new SyCache107Manager(this, properties, classLoader, uri);

            cacheManagersByURI.put(uri, cacheManager);
        }

        if (!this.cacheManagers.containsKey(classLoader)) {
            this.cacheManagers.put(classLoader, cacheManagersByURI);
        }
        return cacheManager;
    }

    private static Properties cloneProperties(Properties properties) {
        Properties clone = new Properties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone;
    }

    @Override
    public ClassLoader getDefaultClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public Properties getDefaultProperties() {
        return new Properties();
    }

    @Override
    public URI getDefaultURI() {
        return URI_DEFAULT;
    }

    @Override
    public boolean isSupported(OptionalFeature arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public void releaseCacheManager(URI uri, ClassLoader classLoader) {
        if (uri == null || classLoader == null) {
            throw new NullPointerException("uri or classLoader should not be null");
        }

        ConcurrentMap<URI, CacheManager> cacheManagersByURI = this.cacheManagers.get(classLoader);
        if (cacheManagersByURI != null) {
            cacheManagersByURI.remove(uri);

            if (cacheManagersByURI.size() == 0) {
                this.cacheManagers.remove(classLoader);
            }
        }
    }
}
