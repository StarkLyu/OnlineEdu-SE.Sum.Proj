package com.se231.onlineedu.exception;

/**
 * @author liu
 */
public class IdentityException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public IdentityException(String msg) {
        this.msg = msg;
    }
}
