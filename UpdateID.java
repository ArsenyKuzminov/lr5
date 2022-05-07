/**
 * command UpdateID which updates ticket with specified id
 */
public class UpdateID extends AbstractCommand{
    public UpdateID() {
        this.name="UpdateID";
        this.help="updates a ticket with specified id(accepts ONE integer parameter as requested id, afterwards manually creates an updated ticket).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command UpdateID only accepts one number parameter.\n");
        else cm.executeUpdateID(args[1]);
    }
}