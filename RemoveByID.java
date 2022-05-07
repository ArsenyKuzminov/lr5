/**
 * command RemoveByID which removes an element with specified id from collection
 */
public class RemoveByID extends AbstractCommand{
    public RemoveByID() {
        this.name="RemoveByID";
        this.help="removes an element with specified id from collection(accepts ONE integer parameter as requested id).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command RemoveByID only accepts one number parameter.\n");
        else cm.executeRemoveByID(args[1]);
    }
}