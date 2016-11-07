package com.weather.app.util;

/**
 * Created by hasee on 2016/11/7.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
