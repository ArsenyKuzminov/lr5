/**
 * command RemoveGreater which removes all the elements which value is greater than given
 */
public class RemoveGreater extends AbstractCommand{
    public RemoveGreater() {
        this.name="RemoveGreater";
        this.help="removes all the elements which value is greater than given(accepts ONE integer parameter as requested hashcode).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command RemoveGreater only accepts one number parameter.\n");
        else cm.executeRemoveGreater(args[1]);
    }
}