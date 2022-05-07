/**
 * command Add which add manually created ticket to collection
 */
public class Add extends AbstractCommand{
    public Add() {
        this.name="Add";
        this.help="adds a manually created ticket to a collection(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Add doesn't accept any parameters.\n");
        else cm.executeAdd();
    }
}