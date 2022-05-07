/**
 * exception, which will be thrown in case of too big value
 */
public class TooBigValueException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public TooBigValueException(String msg) {
        super(msg);
    }
}