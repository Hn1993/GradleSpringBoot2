package com.an.gradlespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{

    private int code; // 0成功
    private String msg;
    private T data;

    /**
     * 失败
     * @return
     */
    public static Result Error(String msg) {
        return new Result<>(2, msg,new Object());
    }

    /**
     * 服务器内部错误
     * @return
     */
    public static Result Exception(String msg) {
        return new Result<>(1, msg,new Object());
    }

    /**
     * 成功
     * @param object
     * @return
     */
    public static Result Success(String msg,Object object) {
        return new Result(0,msg,object);
    }

}
