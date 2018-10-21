package com.sun.storemanager.exception;

import lombok.Data;

/**
 * @author sunjiamin
 */
@Data
public class BaseException extends RuntimeException {

    private String msg;

    public BaseException(String msg){
        super(msg);
        this.msg = msg;
    }
}
