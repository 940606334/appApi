package cn.yearcon.yrcocrmapi.common.execption;

/**
 * @author ayong
 * @create 2018-03-22 10:19
 **/
public class ServiceExecption extends RuntimeException {
    public ServiceExecption() {
    }

    public ServiceExecption(String message) {
        super(message);
    }

    public ServiceExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExecption(Throwable cause) {
        super(cause);
    }

    public ServiceExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
