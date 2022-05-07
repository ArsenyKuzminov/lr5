/**
 * command Info which prints information of current collection state
 */
public class Info extends AbstractCommand{
    public Info() {
        this.name="Info";
        this.help="shows information about collection's current state(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute (CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Info doesn't accept any parameters.\n");
        else cm.executeInfo();
    }
}