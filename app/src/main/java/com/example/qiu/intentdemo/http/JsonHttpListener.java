package com.example.qiu.intentdemo.http;

/**
 * Created by qiu on 2018/2/28.
 */

public interface JsonHttpListener {
    public void onFinish(String response);
    public void onError(Exception e);
}
