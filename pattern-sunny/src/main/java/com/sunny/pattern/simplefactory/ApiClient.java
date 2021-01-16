package com.sunny.pattern.simplefactory;

public class ApiClient {
    public static void main(String[] args) {
        Api api = new ApiImpl();
        api.doApi();
    }
}
