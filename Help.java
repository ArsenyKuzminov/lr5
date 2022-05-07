/**
 * command Help which prints help for available commands
 */
public class Help extends AbstractCommand{
    public Help() {
        this.name="Help";
        this.help="displays help for all available commands(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Help doesn't accept any parameters.\n");
        else cm.executeHelp();
    }
}