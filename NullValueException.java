/**
 * exception, which will be thrown in case of null value
 */
public class NullValueException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public NullValueException(String msg) {
        super(msg);
    }
}