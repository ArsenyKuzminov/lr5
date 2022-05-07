/**
 * command PrintAscending which prints all the elements in the ascending value  
 */
public class PrintAscending extends AbstractCommand{
    public PrintAscending() {
        this.name="PrintAscending";
        this.help="prints all the elements of the collection in the ascending value order(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command PrintAscending doesn't accept any parameters.\n");
        else cm.executePrintAscending();
    }
}