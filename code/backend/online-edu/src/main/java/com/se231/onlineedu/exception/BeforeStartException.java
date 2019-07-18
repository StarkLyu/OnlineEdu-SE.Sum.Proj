package com.se231.onlineedu.exception;
/**
 * @author liu
 * @date 2019/07/11
 */
public class BeforeStartException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg;

    public BeforeStartException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
