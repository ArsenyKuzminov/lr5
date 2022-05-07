import java.io.IOException;

/**
 * abstract class - module of the commands
 */
public abstract class AbstractCommand {
    protected String name;
    protected String help;

    /**
     * return the name of the command
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return the help of the command
     * @return String
     */
    public String getHelp() {
        return help;
    }

    /**
     * execution of the command. references to command code in command manager
     * @param CommandManager
     * @param args
     * @throws IOException, InappropriateArgsException, NullValueException, TooSmallValueException, TooBigValueException
     */
    abstract public void execute(CommandManager cm, String args[]) throws IOException, InappropriateArgsException;
}
