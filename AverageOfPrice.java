/**
 * command AverageOfPrice which counts and prints average price in collection
 */
public class AverageOfPrice extends AbstractCommand{
    public AverageOfPrice() {
        this.name="AverageOfPrice";
        this.help="print the average price value for collection(doesn't accept any parameter).";
    }

    /**
     * execution of the command
     * @param cm
     * @param args
     * @throws InappropriateArgsException
     */
    public void execute(CommandManager cm, String args[]) throws InappropriateArgsException{
        if (args.length>1) throw new InappropriateArgsException("Command AverageOfPrice doesn't accept any parameters.\n");
        else cm.executeAverageOfPrice();
    }
}