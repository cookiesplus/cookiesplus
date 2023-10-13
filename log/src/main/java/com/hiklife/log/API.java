package com.hiklife.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elvishew.xlog.XLog;
import com.hiklife.log.utils.JSResponse;
import com.hiklife.log.utils.ResultUtil;
import com.hiklife.log.utils.XlogUtil;
import com.hiklife.log.vo.input.LogSaveIVO;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class API extends UniModule {
    /**
     * 初始化日志等级
     * @param options
     * @param callback
     */
    @UniJSMethod(uiThread = false)
    public void init(JSONObject options, UniJSCallback callback) {
        try {
            Integer logLevel = options.getInteger("logLevel");
            if (logLevel == null) {
                JSResponse.invokeJSCallback(callback, ResultUtil.fail("日志等级不能为空"));
                return;
            }
            XlogUtil.initXLog(logLevel, this.mWXSDKInstance.getContext());
            JSResponse.invokeJSCallback(callback, ResultUtil.success("开启成功，重启应用后生效"));
        } catch (Exception e) {
            JSResponse.invokeJSCallback(callback, ResultUtil.fail(e.getMessage()));
        }
    }

    /**
     * 保存日志信息
     *
     * //  public static final int VERBOSE = 2;
     * //  public static final int DEBUG = 3;
     * //  public static final int INFO = 4;
     * //  public static final int WARN = 5;
     * //  public static final int ERROR = 6;
     * //  public static final int ASSERT = 7;
     *
     * @param options
     */
    @UniJSMethod(uiThread = false)
    public void save(JSONObject options, UniJSCallback callback) {
        try {
            LogSaveIVO logSaveIVO = JSON.toJavaObject(options, LogSaveIVO.class);
            String msg = logSaveIVO.getMsg();
            if (logSaveIVO.getLogLevel() != null) {
                switch (logSaveIVO.getLogLevel()) {
                    case 2:
                        XLog.v(msg);
                        break;
                    case 3:
                        XLog.d(msg);
                        break;
                    case 4:
                        XLog.i(msg);
                        break;
                    case 5:
                        XLog.w(msg);
                        break;
                    case 6:
                        XLog.e(msg);
                        break;
                    default:
                        XLog.i(msg);
                }
            } else {
                XLog.i(logSaveIVO.getMsg());
            }
            JSResponse.invokeJSCallback(callback, ResultUtil.success("日志写入成功"));
        } catch (Exception e) {
            JSResponse.invokeJSCallback(callback, ResultUtil.fail(e.getMessage()));
        }
    }

    @UniJSMethod(uiThread = false)
    public void show(UniJSCallback callback) {
        try {
            JSResponse.invokeJSCallback(callback, ResultUtil.success());
        } catch (Exception e) {
            JSResponse.invokeJSCallback(callback, ResultUtil.fail(e.getMessage()));
        }
    }

}
