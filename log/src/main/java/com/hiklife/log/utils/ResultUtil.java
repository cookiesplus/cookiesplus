package com.hiklife.log.utils;


public class ResultUtil {
    private Boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultUtil success() {
        return resultData(true, CodeEnum.SUCCESS.val(), CodeEnum.SUCCESS.msg(), null);
    }

    public static ResultUtil success(Object data) {
        return resultData(true, CodeEnum.SUCCESS.val(), CodeEnum.SUCCESS.msg(), data);
    }

    public static ResultUtil success(Object data, String msg) {
        return resultData(true, CodeEnum.SUCCESS.val(), msg, data);
    }

    public static ResultUtil fail(String msg) {
        return resultData(false, CodeEnum.ERROR.val(), msg, null);
    }

    public static ResultUtil fail(Integer code, String msg) {
        return resultData(false, code, msg, null);
    }

    public static ResultUtil fail(Integer code, String msg, Object data) {
        return resultData(false, code, msg, data);
    }

    private static ResultUtil resultData(Boolean success, Integer code, String msg, Object data) {
        ResultUtil resultData = new ResultUtil();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        resultData.setSuccess(success);
        return resultData;
    }
}
