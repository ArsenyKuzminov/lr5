/**
 * command AddIfMax which adds element to collection if it bigger than others
 */
public class AddIfMax extends AbstractCommand{
    public AddIfMax() {
        this.name="AddIfMax";
        this.help="adds a new element to collection, if it's value is bigger than others(doesn't accept any parameter, you manually creates ticket for this operation).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command AddIfMax doesn't accept any parameters.\n");
        else cm.executeAddIfMax();
    }
}