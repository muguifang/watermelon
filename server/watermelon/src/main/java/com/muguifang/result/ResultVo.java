package com.muguifang.result;

import lombok.Data;

@Data
public class ResultVo<T> {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    public ResultVo() {
    }

    /**
     * 用于后台有数据返回前台构造器
     * @param code
     * @param msg
     * @param t
     */
    public ResultVo(Integer code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    /**
     * 用于后台无数据返回前台构造器
     * @param code
     * @param msg
     */
    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 静态方法 对应有数据返回
     * @param code
     * @param msg
     * @param t
     * @return
     */
    public static ResultVo sendResult(Integer code, String msg, Object t) {
        return new ResultVo(code, msg, t);
    }

    /**
     * 静态方法 对应无jdbc.properties数据返回
     * @param code
     * @param msg
     * @return
     */
    public static ResultVo sendResult(Integer code, String msg) {
        return new ResultVo(code, msg);
    }

    public int getCode() {
        return this.code;
    }

    public T getData(){
        return this.data;
    }
}
