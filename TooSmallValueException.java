/**
 * exception, which will be thrown in case of too long string
 */
public class TooSmallValueException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public TooSmallValueException(String msg) {
        super(msg);
    }
}