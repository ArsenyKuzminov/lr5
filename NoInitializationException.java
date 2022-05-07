/**
 * exception, which will be thrown in case not initialized collection
 */
public class NoInitializationException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public NoInitializationException(String msg) {
        super(msg);
    }
}