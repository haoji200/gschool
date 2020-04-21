package com.atguigu.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * R
 *
 * @Author: wd
 * @CreateTime: 2020-04-06
 * @Description:
 */
@Data
public class R {

    private Boolean success; //是否成功
    private Integer code;//状态码 20000  20001
    private String message; //提示信息
    //数据
    private Map<String, Object> data = new HashMap<String, Object>();

    private R(){}

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20000);
        r.setMessage("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
