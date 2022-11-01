package my.repo.common.exception;


/**
 * @author gengzhihao
 * @date 2022/11/1 15:59
 * @description 入参为空异常
**/

public class InputIsNullException extends RuntimeException{

    public InputIsNullException() {
        super();
    }

    public InputIsNullException(String message) {
        super(message);
    }

    public InputIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputIsNullException(Throwable cause) {
        super(cause);
    }

    protected InputIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
