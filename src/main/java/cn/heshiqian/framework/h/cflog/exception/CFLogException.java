package cn.heshiqian.framework.h.cflog.exception;

public class CFLogException extends RuntimeException {

    public CFLogException(String message) {
        super(message);
    }

    public CFLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public CFLogException(Throwable cause) {
        super(cause);
    }

    protected CFLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
