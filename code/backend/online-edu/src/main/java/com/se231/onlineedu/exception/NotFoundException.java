package com.se231.onlineedu.exception;


/**
 * @author liu
 */
public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public NotFoundException(String msg) {
        this.msg = msg;
    }
}
