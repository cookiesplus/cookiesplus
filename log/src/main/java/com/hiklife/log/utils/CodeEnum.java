package com.hiklife.log.utils;

public enum CodeEnum {
    /** 成功 */
    SUCCESS(200, "成功"),

    /** 操做失败 */
    ERROR(400, "操做失败");

    CodeEnum(Integer value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public Integer val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private Integer val;
    private String msg;
}
