package com.example.springbootdemo.handle;

import com.example.springbootdemo.domain.Result;
import com.example.springbootdemo.exception.GirlException;
import com.example.springbootdemo.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHanlde {

    //希望返回给浏览器的是一个Result的对象
    @ExceptionHandler(value = Exception.class)//声明要捕获的类名
    @ResponseBody//返回一个json格式对象给浏览器
    public Result handle(Exception e){
        if(e instanceof GirlException){
            GirlException girlException = (GirlException)e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }else{
            return ResultUtil.error(-1, "未知错误");
        }

    }
}
