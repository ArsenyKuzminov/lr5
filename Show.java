/**
 * command Show which prints all elements of the collection
 */
public class Show extends AbstractCommand{
    public Show() {
        this.name="Show";
        this.help="prints all the elements of the collection(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command Show doesn't accept any parameters.\n");
        else cm.executeShow();
    }
}