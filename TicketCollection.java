import java.util.TreeSet;

/**
 *class to work with collection of tickets
 */
public class TicketCollection {
    private static TreeSet<Ticket> tickets;
    private static boolean initialization=false;
    private static java.time.LocalDateTime initializationTime;

    /**
     * return treeset, throw exception if it wasn't initialized
     * @return treeset
     * @throws NoInitializationException
     */
    public static TreeSet<Ticket> getTickets() throws NoInitializationException{
        if (!initialization) throw new NoInitializationException("Collection wasn't initialized.\n");
        else return tickets;
    }

    /**
     * do initialization of collection and sets initialization time
     */
    public static void doInitialization() {
        if (!initialization) {
            tickets = new TreeSet<>();
            initialization=true;
            initializationTime=java.time.LocalDateTime.now();
        }
    }

    /**
     * getter of initialization time
     * @return LocalDate
     */
    public static java.time.LocalDateTime getInitializationTime(){
        return initializationTime;
    }

    /**
     * initialization getter
     * @return
     */
    public static boolean getInitialization() {
        return initialization;
    }
}