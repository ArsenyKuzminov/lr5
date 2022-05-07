/**
 * command Exit which exits program
 */
public class Exit extends AbstractCommand{
    public Exit() {
        this.name="Exit";
        this.help="exits the program(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Exit don't accept any parameters.\n");
        else cm.executeExit();
    }
}