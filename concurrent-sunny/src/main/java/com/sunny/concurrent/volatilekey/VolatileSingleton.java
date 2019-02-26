package com.sunny.concurrent.volatilekey;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/19 14:22 <br>
 * @see com.sunny.concurrent.volatilekey <br>
 */
public class VolatileSingleton {
    public volatile static VolatileSingleton instance;

    public static VolatileSingleton getInstance(){
        if(instance == null){
            synchronized(VolatileSingleton.class){
                if(instance== null){
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        VolatileSingleton.getInstance();
    }

}
