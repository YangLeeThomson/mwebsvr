package com.uec.imonitor.common.exception;


/**
 * <br/>Description:获取新闻网站名字拼音对应的异常处理类
 * <p>Author:xkwang/王西坤</p>
 */
public class BadHanYuPinYinException extends RuntimeException {

    private static final long serialVersionUID = 4447260855879734366L;

    public BadHanYuPinYinException() {
    }

    public BadHanYuPinYinException(String message) {
        super(message);
    }

    public BadHanYuPinYinException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadHanYuPinYinException(Throwable cause) {
        super(cause);
    }
}
