package com.se231.onlineedu.exception;

/**
 * @author liu
 */
public class EndBeforeStartException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public EndBeforeStartException(String msg) {
        this.msg = msg;
    }
}
