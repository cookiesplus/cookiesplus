package com.hiklife.log.utils;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import io.dcloud.feature.uniapp.bridge.UniJSCallback;

public class JSResponse {
    static String TAG = JSResponse.class.getSimpleName();

    public static void invokeJSCallback(UniJSCallback callback, ResultUtil resultUtil) {
        invokeJSCallback(callback, resultUtil, false);
    }

    public static void invokeJSCallback(UniJSCallback callback, ResultUtil resultUtil, Boolean isKeepAlive) {
        invokeJSCallback(callback, resultUtil.getSuccess(), resultUtil.getCode(), resultUtil.getMsg(), resultUtil.getData(), isKeepAlive);
    }

    public static void invokeJSCallback(UniJSCallback callback, Boolean success, Integer code, String msg, Object data, Boolean isKeepAlive) {
        if (callback == null) {
            Log.d(TAG, "callback invoke null");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        jsonObject.put("success", success);
        if (isKeepAlive) {
            callback.invokeAndKeepAlive(jsonObject);
        } else {
            callback.invoke(jsonObject);
        }
    }
}
