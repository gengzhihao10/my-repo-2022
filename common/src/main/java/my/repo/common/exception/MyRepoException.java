package my.repo.common.exception;

public class MyRepoException extends RuntimeException{

    public MyRepoException() {
        super();
    }

    public MyRepoException(String message) {
        super(message);
    }

    public MyRepoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRepoException(Throwable cause) {
        super(cause);
    }

    protected MyRepoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
