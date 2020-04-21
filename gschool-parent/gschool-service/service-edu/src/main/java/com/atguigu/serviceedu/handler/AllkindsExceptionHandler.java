package com.atguigu.serviceedu.handler;

import com.atguigu.common.ExceptionUtil;
import com.atguigu.common.R;
import com.atguigu.serviceedu.handler.myException.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler
 *
 * @Author: wd
 * @CreateTime: 2020-04-07
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class AllkindsExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R ExceptionAOP(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R ArithmeticExceptionAOP(ArithmeticException a){
        a.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R MyExceptionAOP(MyException m){
        m.printStackTrace();
//        log.error(m.getMsg());
        log.error(ExceptionUtil.getMessage(m));
        return R.error().message(m.getMsg()).code(m.getCode());
    }
}