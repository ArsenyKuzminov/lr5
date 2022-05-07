import java.io.IOException;

/**
 * command Save which saves collection to predesignated file
 */
public class Save extends AbstractCommand{
    public Save() {
        this.name="Save";
        this.help="saves collection in the predesignated file(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException, IOException{
        if (args.length>1) throw new InappropriateArgsException("Command Save don't accept any parameters.\n");
        else cm.executeSave();
    }
}