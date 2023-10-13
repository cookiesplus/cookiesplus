package com.hiklife.log.vo.input;

public class LogSaveIVO {
    //  public static final int VERBOSE = 2;
    //  public static final int DEBUG = 3;
    //  public static final int INFO = 4;
    //  public static final int WARN = 5;
    //  public static final int ERROR = 6;
    //  public static final int ASSERT = 7;
    private Integer logLevel;
    private String msg;

    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
