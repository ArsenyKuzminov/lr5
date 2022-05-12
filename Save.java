import java.io.IOException;

/**
 * command Save which saves collection to predesignated file
 */
public class Save extends AbstractCommand{
    public Save() {
        this.name="Save";
        this.help="saves collection in the predesignated file(accepts ONE string parameter as file name).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException, IOException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command Save accepts only one string parameter.\n");
        else cm.executeSave(args[1]);
    }
}