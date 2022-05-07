/**
 * exception, which will be thrown in case of inappropriate args for a command
 */
public class InappropriateArgsException extends RuntimeException{

    /**
     * constructor
     * @param msg
     */
    public InappropriateArgsException(String msg) {
        super(msg);
    }
}