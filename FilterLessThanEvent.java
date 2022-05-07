/**
 * command FilterLessThanEvent which prints elements which event's value is less than given
 */
public class FilterLessThanEvent extends AbstractCommand{
    public FilterLessThanEvent() {
        this.name="FilterLessThanEvent";
        this.help="prints elements which event's value is less than given (as argument accepts csv string of event as string(name),date(yyyy-mm-day),longInteger(min age),integer(ticketscount>0),string(descryption<=1017)).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>2||args.length==1) throw new InappropriateArgsException("Command FilterLessThanEvent only accept only one event parameters.\n");
        else cm.executeFilterLessThanEvent(args[1]);
    }
}