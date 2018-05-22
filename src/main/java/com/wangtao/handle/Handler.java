package com.wangtao.handle;


import com.wangtao.domain.Result;
import com.wangtao.exception.GirlException;
import com.wangtao.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Handler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
public Result handle(Exception e ){
        if (e instanceof GirlException){
            GirlException girlException= (GirlException) e;
            return ResultUtil.error(((GirlException) e).getCode(), e.getMessage());
        }
    return ResultUtil.error(0, "未知错误");
    }

}
