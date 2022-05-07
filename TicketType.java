/**
 * enum, which specializes tickets' types
 */
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    /**
     * return all elements in string
     * @return String
     */
    public static String List() {
        String list = "";
        for (TicketType ticketType:TicketType.values()) {
            list=list+ticketType.name()+"/n";
        }
        return list;
    }
}
