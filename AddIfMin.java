/**
 * command AddIfMin which adds element to collection if it smaller than others
 */
public class AddIfMin extends AbstractCommand{
    public AddIfMin() {
        this.name="AddIfMin";
        this.help="adds a new element to collection, if it's value is smaller than others(doesn't accept any parameter, you manually creates ticket for this operation).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command AddIfMin doesn't accept any parameters.\n");
        else cm.executeAddIfMin();
    }
}