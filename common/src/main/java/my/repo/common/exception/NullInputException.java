package my.repo.common.exception;


/**
 * @author gengzhihao
 * @date 2022/11/1 15:59
 * @description 入参为空异常
**/

public class NullInputException extends MyRepoException{

    public NullInputException() {
        super();
    }

    public NullInputException(String message) {
        super(message);
    }

    public NullInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullInputException(Throwable cause) {
        super(cause);
    }

    protected NullInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
