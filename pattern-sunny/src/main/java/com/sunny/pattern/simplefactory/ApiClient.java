package com.sunny.pattern.simplefactory;

/**
 * @description: TODO
 * @author Sunny
 * @date 2021/1/17 2:28 下午
 * @version 1.0
 */
public class ApiClient {
    public static void main(String[] args) {
        Api api = new ApiImpl();
        api.doApi();
    }
}
