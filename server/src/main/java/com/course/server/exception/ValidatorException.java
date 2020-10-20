package com.course.server.exception;

/**
 * @author: xyl
 * @time: 2020/10/20 17:12
 * @description:
 */
public class ValidatorException extends RuntimeException{
    public ValidatorException(String message){
        super(message);
    }
}
