package com.se231.onlineedu.exception;

/**
 * @author liu
 */
public class FileFormatNotSupportException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public FileFormatNotSupportException(String msg) {
        this.msg = msg;
    }
}
