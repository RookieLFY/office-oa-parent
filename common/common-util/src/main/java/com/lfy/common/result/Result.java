package com.lfy.common.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code; //测试接口时返回的状态码：例如200，201等
    private String message; //测试接口时返回的信息例如：成功，失败等
    private T data; //测试接口时返回的具体数据

    //构造私有化
    private Result(){
    }

    //封装返回的数据:其一具备返回数据、状态码以及返回信息
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = new Result<>();
        //封装数据
        if (body != null){
            result.setData(body);
        }
        //填充状态码
        result.setCode(resultCodeEnum.getCode());
        //填充信息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    //返回成功的方法
    public static<T> Result<T> ok(){
        return build(null,ResultCodeEnum.SUCCESS);
    }
    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }


    //返回失败的方法
    public static<T> Result<T> fail(){
        return build(null,ResultCodeEnum.FAIL);
    }
    public static<T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

    //自定义设置返回信息
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    //自定义设置返回状态码
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
