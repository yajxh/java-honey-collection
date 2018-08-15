package com.sunny.cache.sycache.store;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 14:02 <br>
 * @see com.sunny.cache.sycache.store <br>
 */
public class StoreAccessException extends Exception {
    public StoreAccessException() {
        super();
    }

    public StoreAccessException(String message) {
        super(message);
    }

    public StoreAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreAccessException(Throwable cause) {
        super(cause);
    }

    protected StoreAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
