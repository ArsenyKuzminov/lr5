/**
 * exception, which will be thrown in case of too long string
 */
public class TooLongStringException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public TooLongStringException(String msg) {
        super(msg);
    }
}