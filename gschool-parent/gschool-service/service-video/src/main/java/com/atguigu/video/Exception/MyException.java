package com.atguigu.video.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MyException
 *
 * @Author: wd
 * @CreateTime: 2020-04-07
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{

    private Integer code;

    private  String Msg;

    @Override
    public String toString() {
        return "MyException{" +
                "message=" + this.getMsg() +
                ", code=" + code +
                '}';
    }

}