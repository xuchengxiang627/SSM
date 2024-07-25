package com.xcx.utils;
/**
 * description: 返回结果类
 */
public class Result {

    private int code = 200; // 200成功状态码

    private boolean flag = true; // 返回状态

    private Object data;  // 返回具体数据


    public static Result ok(Object data){
        Result r = new Result();
        r.data = data;
        return r;
    }

    public static Result fail(Object data){
        Result r = new Result();
        r.code = 500; // 错误码
        r.flag = false; // 错误状态
        r.data = data;
        return r;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}