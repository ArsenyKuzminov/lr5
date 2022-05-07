/**
 * exception, which will be thrown in case of wrong command
 */
public class NoSuchCommandException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public NoSuchCommandException(String msg) {
        super(msg);
    }
}