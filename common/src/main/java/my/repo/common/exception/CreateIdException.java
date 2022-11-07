package my.repo.common.exception;


/**
 * @author gengzhihao
 * @date 2022/11/7 11:01
 * @description 构造雪花算法id异常
**/

public class CreateIdException extends MyRepoException{
    public CreateIdException() {
        super();
    }

    public CreateIdException(String message) {
        super(message);
    }

    public CreateIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateIdException(Throwable cause) {
        super(cause);
    }

    protected CreateIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
