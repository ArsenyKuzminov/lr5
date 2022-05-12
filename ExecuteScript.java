import java.io.IOException;

/**
 * command ExecuteScript which executes command list from file
 */
public class ExecuteScript extends AbstractCommand{
    public ExecuteScript() {
        this.name="ExecuteScript";
        this.help="executes script from file(accepts ONE string parameter as filename with script).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException, IOException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command ExecuteScript only accepts one string parameter.\n");
        else {
            cm.executeExecuteScript(args[1]);
        }
    }
}