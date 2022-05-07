/**
 * command Clear which clears collection
 */
public class Clear extends AbstractCommand{
    public Clear() {
        this.name="Clear";
        this.help="purges collection from all elements(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Clear doesn't accept any parameters.\n");
        else cm.executeClear();
    }
}