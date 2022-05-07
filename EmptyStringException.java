/**
 * exception, which will be thrown in case of empty string
 */
public class EmptyStringException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public EmptyStringException(String msg) {
        super(msg);
    }
}