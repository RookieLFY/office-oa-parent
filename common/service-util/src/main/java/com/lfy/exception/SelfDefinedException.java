package com.lfy.exception;

import com.lfy.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class SelfDefinedException extends RuntimeException{
    private Integer code;
    private String msg;

    public SelfDefinedException(Integer code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public SelfDefinedException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "SelfDefinedException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
