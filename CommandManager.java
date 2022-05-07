import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * class command manager which controls command execution
 */
public class CommandManager {
    private static ArrayList<AbstractCommand> commands = new ArrayList<>();
    private boolean find=false;


    /**
     * constructor. initialization of commands
     */
    public CommandManager() {
        commands.add(new Add());
        commands.add(new AddIfMax());
        commands.add(new AddIfMin());
        commands.add(new AverageOfPrice());
        commands.add(new Clear());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new FilterLessThanEvent());
        commands.add(new Help());
        commands.add(new Info());
        commands.add(new PrintAscending());
        commands.add(new RemoveByID());
        commands.add(new RemoveGreater());
        commands.add(new Save());
        commands.add(new Show());
        commands.add(new UpdateID());
    }

    /**
     * get static HashSet<AbstractCommand> commands
     * @return HashSet<AbstractCommand>
     */
    public ArrayList<AbstractCommand> getCommands(){
        return commands;
    }

    /**
     * Help execution through iterator
     */
    public void executeHelp() {
        Iterator<AbstractCommand> iter=commands.iterator();
        while (iter.hasNext()) {
            AbstractCommand A = iter.next();
            System.out.print(A.getName()+": "+A.getHelp()+"\n");
        }
    }

    /**
     * Info execution
     */
    public void executeInfo() {
        if (!TicketCollection.getInitialization()) System.out.print("Collection wasn't initialized.\n");
        else System.out.print("Collection Type: " + TicketCollection.getTickets().getClass()+".\nAmount of elements: "+TicketCollection.getTickets().size()+".\nInitializationTime: "+TicketCollection.getInitializationTime()+".\n");
    }

    /**
     * Show execution through iterator and csv formatting
     */
    public void executeShow() {
        if(TicketCollection.getTickets().size()==0) System.out.print("Ticket Collection is empty.\n");
        else System.out.print(Tools.PrintTicketSet(TicketCollection.getTickets()));
    }

    /**
     * Add execution
     */
    public void executeAdd() {
        TicketCollection.doInitialization();
        Ticket t = Ticket.TicketCreation();
        if (t==null) System.out.print("Ticket with inappropriate parameters created. It won't be added to collection.\n");
        else {
            TicketCollection.getTickets().add(t);
            System.out.print("Ticket successfully added to collection.\n");
        }
    }

    /**
     * UpdateID execution
     * @param in
     */
    public void executeUpdateID(String in) throws InappropriateArgsException {
        Long id=Long.valueOf(in);
        Ticket t;
        Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
        out: while(iter.hasNext()) {
            if(findByID(id)==null) throw new InappropriateArgsException("No such ID.\n");
            if((t=iter.next()).getID().equals(id));
            TicketCollection.getTickets().remove(t);
            System.out.print("Please, update this element.\n");
            Ticket insert = Ticket.TicketCreation();
            insert.changeID(id);
            Ticket.balanceID();
            TicketCollection.getTickets().add(insert);
            break out;
        }
    }

    /**
     * id search utility method
     * @param id
     * @return Ticket
     */
    private Ticket findByID(Long id) {
        Ticket t=null;
        Ticket tckt;
        Iterator<Ticket> iter=TicketCollection.getTickets().iterator();
        out:while(iter.hasNext()) {
            if ((tckt=iter.next()).getID().equals(id)) {
                t=tckt;
                find=true;
                break out;
            }
        }
        return t;
    }

    /**
     * RemoveByID execution
     * @param in
     */
    public void executeRemoveByID(String in) {
        Long id=Long.valueOf(in);
        Ticket t=findByID(id);
        if(!find) throw new InappropriateArgsException("No Ticket with such ID.\n");
        if (t!=null) TicketCollection.getTickets().remove(t);
    }

    /**
     * Clear execution
     */
    public void executeClear() {
        TicketCollection.getTickets().clear();
    }

    /**
     * Save execution
     */
    public void executeSave() throws IOException {
        Tools.Save();
    }

    /**
     * ExecuteScript execution from selected file
     * @param name
     * @param commandManager
     * @throws IOException
     * @throws InappropriateArgsException
     */
    public void executeExecuteScript(String name,CommandManager commandManager) throws IOException,InappropriateArgsException{
        FileReader fr = new FileReader(name);
        Scanner fileScanner = new Scanner(fr);
        String commandtext;
        AbstractCommand abstractCommand;
        while(fileScanner.hasNextLine()){
            commandtext = fileScanner.nextLine();
            String []line=commandtext.split(" ");
            Iterator<AbstractCommand> iter = commands.iterator();
            while(iter.hasNext()){
                if((abstractCommand=iter.next()).getName().equalsIgnoreCase(line[0])){
                    if(abstractCommand!=null&&!abstractCommand.getName().equalsIgnoreCase("ExecuteScript")) {
                        abstractCommand.execute(commandManager, line);
                    }
                }
            }
        }
    }


//    much more interestring realisation of script execution, which weren't tested enough
//    maybe I'll add it later
//    public void executeExecuteScript(String string,CommandManager commandManager) throws IOException,InappropriateArgsException{
//        Stack<String> scripts = new Stack<>();
//        Stack<Integer> strings = new Stack<>();
//        scripts.push(string);
//        Integer fso = 0; //first string offset
//        strings.push(fso);
//        String path = string;
//        boolean ex;
//        String commandtext="";
//        prescript: while(!scripts.isEmpty()) {
//            path=scripts.pop();
//            fso=strings.pop();
//            FileReader file = new FileReader(path);
//            Scanner sc = new Scanner(file);
//            Tools.skipLines(sc, fso);
//            AbstractCommand command;
//            while (sc.hasNext()) {
//                fso++;
//                commandtext = sc.nextLine();
//                String[] commandLine = commandtext.split(" ");
//                Iterator<AbstractCommand> iterator = CommandManager.commands.iterator();
//                while (iterator.hasNext()) {
//                if ((command = iterator.next()).getName().equalsIgnoreCase(commandLine[0])) {
//                if (!command.getName().equalsIgnoreCase("executescript")) {
//                     command.execute(commandManager, commandLine);
//                        } else {
//                            ex = true;
//                            File example = new File(commandLine[1]);
//                            Iterator<String> iter = scripts.iterator();
//                            out:
//                            while (iter.hasNext()) {
//                                String fn = iter.next();
//                                if (fn.equals(commandLine[1])) ex = false;
//                            }
//                            if (ex = true && example.isFile()) {
//                                if (!example.canRead()) example.setReadable(true);
//                                scripts.push(path);
//                                scripts.push(commandLine[1]);
//                                strings.push(fso);
//                                strings.push(0);
//                                sc.close();
//                                file.close();
//                                continue prescript;
//                            } else System.out.print("File isn't valid and usable, proceeding to the next command.\n");
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     * utility method for command search
     * @param name
     * @return AbstractCommand
     */
    public static AbstractCommand findCommand(String name) {
        AbstractCommand A=null;
        AbstractCommand B;
        Iterator<AbstractCommand> iter= commands.iterator();
        while (iter.hasNext())
            if ((B=iter.next()).getName().equalsIgnoreCase(name)) A=B;
        return A;
    }

    /**
     * Exit execution
     */
    public void executeExit() {
        System.out.print("Exiting the program...\n");
        System.exit(2);
    }

    /**
     * AddIfMax execution
     */
    public void executeAddIfMax() {
        boolean res=true;
        System.out.print("Please, configure ticket you want to add.\n");
        Ticket t=Ticket.TicketCreation();
        Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
        while (iter.hasNext()) {
            if (t.compareTo(iter.next())<1) res=false;
        }
        if (res==false) System.out.print("Your ticket isn't max, so it won't be added to collection.\n");
        else {
            TicketCollection.getTickets().add(t);
            System.out.print("Your ticket is max and follow all the requirments so it already added to collection.\n");
        }
    }

    /**
     * AddIfMin execution
     */
    public void executeAddIfMin() {
        boolean res=true;
        System.out.print("Please, configure ticket you want to add.\n");
        Ticket t=Ticket.TicketCreation();
        Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
        while (iter.hasNext()) {
            if (t.compareTo(iter.next())>-1) res=false;
        }
        if (res==false) System.out.print("Your ticket isn't min, so it won't be added to collection.\n");
        else {
            TicketCollection.getTickets().add(t);
            System.out.print("Your ticket is min and follow all the requirments so it already added to collection.\n");
        }
    }

    /**
     * RemoveGreaterExecution
     * @param in
     */
    public void executeRemoveGreater(String in) {
        Integer i = Integer.valueOf(in);
        Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
        Ticket t;
        while (iter.hasNext()) if(iter.next().hashCode()>i) iter.remove();
    }

    /**
     * AverageOfPrice execution
     */
    public void executeAverageOfPrice() {
        if(TicketCollection.getTickets().size()==0) {
            System.out.print("Collection is empty so price can't be calculated.\n");
            return;
        }
        Long price = Long.valueOf(0);
        Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
        while(iter.hasNext()) price=price+iter.next().getPrice();
        System.out.print("Average price is:"+price/TicketCollection.getTickets().size()+".\n");
    }

    /**
     * FilterLessThanEvent execution
     * @param in
     */
    public void executeFilterLessThanEvent(String in) {
        Event event=Event.parse(in);
        if (event!=null){
            int ev = event.hashCode();
            Ticket t;
            System.out.print("Tickets with Event's value less than given:\n");
            System.out.print("id,name,x,y,creationDate,comment,type,eventID,eventName,eventDate,eventMinAge,eventTicketsCount,eventDescryption\n");
            Iterator<Ticket> iter = TicketCollection.getTickets().iterator();
            while (iter.hasNext()) {
                if ((t = iter.next()).getEvent().hashCode() < ev) {
                    System.out.print(t.toString());
                }
            }
        }
        else System.out.print("Event can't be parsed correctly. Aborting an operation.\n");
    }

    /**
     * PrintAscending execution
     * since TreeSet automatically sorts elements, execution by just print
     */
    public void executePrintAscending() {
        System.out.print(Tools.PrintTicketSet(TicketCollection.getTickets()));
    }
}