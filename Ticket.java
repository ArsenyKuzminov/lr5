import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

/**
 * class for collection - Ticket
 *
 */
public class Ticket implements Comparable<Ticket>{
    private long id; //>0, unique, auto
    private String name; //!=empty, !=null
    private Coordinates coordinates; //!=null
    private java.time.LocalDateTime creationDate; //!=null, auto
    private long price; //>0
    private String comment; //length<=1009, cb null
    private TicketType type; //cb null
    private Event event; //!=null
    private static long ticketID=0;

    /**
     * constructor
     * @param name
     * @param coordinates
     * @param price
     * @param comment
     * @param type
     * @param event
     * @throws NullValueException, TooLongStringException, EmptyStringException, TooSmallValueException
     */
    public Ticket (String name, Coordinates coordinates, long price, String comment, TicketType type, Event event) throws NullValueException, TooLongStringException, EmptyStringException, TooSmallValueException{
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setComment(comment);
        setTicketType(type);
        setEvent(event);
        setID();
        setDate();
    }

    /**
     * id setter
     */
    public void setID() {
        ticketID++;
        this.id=ticketID;
    }

    /**
     * id getter
     * @return Long
     */
    public Long getID() {
        return this.id;
    }

    /**
     * id changer for updated tickets
     */
    public void changeID(Long id) {
        this.id=id;
    }

    /**
     * id balancer for updates
     */
    public static void balanceID() {
        ticketID--;
    }

    /**
     * date setter
     */
    public void setDate() {
        this.creationDate=LocalDateTime.now();
    }

    /**
     * name setter. throw exception if value is inappropriate
     * @param name
     * @throws EmptyStringException, NullValueException
     */
    public void setName(String name) throws EmptyStringException, NullValueException{
        if (name==null||name.equals("")) throw new NullValueException("Name can't be null.\n");
        else if (name.trim().equals("")) throw new EmptyStringException("Name can't be empty.\n");
        else this.name=name;
    }

    /**
     * name getter
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * coordinates setter. throw exception if value is inappropriate
     * @param coordinates
     * @throw NullValueException
     */
    public void setCoordinates(Coordinates coordinates) throws NullValueException{
        if (coordinates==null) throw new NullValueException("Coordinates can't be null.\n");
        else this.coordinates=coordinates;
    }

    /**
     * coordinates getter
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * price setter. throw exception if value is inappropriate
     * @param price
     * @throws TooSmallValueException
     */
    public void setPrice(long price) throws TooSmallValueException{
        if (price<=0) throw new TooSmallValueException("Price must be positive.\n");
        else this.price=price;
    }

    /**
     * price getter
     * @return Long
     */
    public Long getPrice() {
        return price;
    }

    /**
     * comment setter. throw exception if value is inappropriate
     * @param comment
     * @throws TooLongStringException
     */
    public void setComment(String comment) throws TooLongStringException{
        if (comment.length()>1009) throw new TooLongStringException("Comment can't be longer than 1009 symbols.\n");
        else this.comment=comment;
    }

    /**
     * comment getter
     * @return String
     */
    public String getComment() {
        return comment;
    }

    /**
     * type setter
     * @param type
     */
    public void setTicketType(TicketType type) {
        this.type=type;
    }

    /**
     * type getter
     * @return TicketType
     */
    public String getTicketType() {
        if (type==null) return "null";
        return String.valueOf(type);
    }

    /**
     * event setter. throw exception if value is inappropriate
     * @param event
     * @throws NullValueException
     */
    public void setEvent(Event event) throws NullValueException{
        if (event==null) throw new NullValueException("Event can't be null.\n");
        else this.event=event;
    }

    /**
     * event getter
     * @return Event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * implements from comparable
     * uses to comparation within collection
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Ticket o) {
        if (o==null||this==null) throw new NullPointerException("Null can't be compared.\n");
        else if (this.equals(o)) return 0;
        else if (this.hashCode()>o.hashCode()) return 1;
        else return -1;
    }

    /**
     * overridden hash code method
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, comment, type, event);
    }

    /**
     * overriden equals method to object comparison
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null||this.getClass()!=o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id==ticket.id&&name.equals(ticket.name)&&coordinates.equals(ticket.coordinates)&&
                creationDate.equals(ticket.creationDate)&&price==ticket.price&&comment.equals(ticket.comment)&&
                type.equals(ticket.type)&&event.equals(ticket.event);
    }

    /**
     * converts object's information into csv format
     * @return String
     */
    @Override
    public String toString() throws NullPointerException {
        return this.id + "," + this.name + "," + this.coordinates.toString() + "," + this.creationDate.toString() + "," + this.price + "," + this.comment + "," + this.type + "," + this.event.toString() + "\n";
    }
    /**
     * manual ticket creation
     * @return Ticket
     * @throws NullValueException
     * @throws TooBigValueException
     * @throws EmptyStringException
     * @throws NumberFormatException
     */
    public static Ticket TicketCreation() throws NullValueException, TooBigValueException, TooSmallValueException, EmptyStringException, NumberFormatException{
        if (!CommandManager.getSEIP()) {
            System.out.print("All the parameter values invalidation will cancel creation straight away.\n");
            String in;
            System.out.print("Input ticket name(can't be empty or null):\n");
            String name = Tools.input();
            if (name.equalsIgnoreCase("")) throw new NullValueException("Name can't be null.\n");
            if (name.trim().equalsIgnoreCase("")) throw new EmptyStringException("Name can't be empty.\n");
            System.out.print("Input x parameter of coordinates (Integer below or equal 484):\n");
            in = Tools.input();
            Integer x = Integer.valueOf(in);
            if (x > 484) throw new TooBigValueException("x can't be bigger than 484.\n");
            System.out.print("Input y parameter of coordinates:\n");
            in = Tools.input();
            Integer y = Integer.valueOf(in);
            System.out.print("Input price(positive integer):\n");
            in = Tools.input();
            Long price = Long.valueOf(in);
            if (price <= 0) throw new TooSmallValueException("Price can't be negative or zero.\n");
            System.out.print("Input comment (amount of characters is below or equal 1009):\n");
            String comment = Tools.input();
            if (comment.length() > 1009)
                throw new TooLongStringException("Comment's char length can't be bigger than 1009.\n");
            System.out.print("Input ticket type(USUAL, VIP, BUDGETARY or CHEAP; in other case it will be null):\n");
            in = Tools.input();
            in.trim();
            in.toUpperCase();
            TicketType type;
            try {
                type = TicketType.valueOf(in);
            } catch (IllegalArgumentException e) {
                System.out.print("Ticket Type cannot be identified as one. Value set as \"null\".\n");
                type = null;
            }
            System.out.print("Input event's name (can't be empty):\n");
            String eventName = Tools.input();
            if (name.equalsIgnoreCase("")) throw new NullValueException("Event Name can't be null.\n");
            if (name.trim().equalsIgnoreCase("")) throw new EmptyStringException("Event Name can't be empty.\n");
            System.out.print("Input date of event(in format year-mm-dd):\n");
            in = Tools.input();
            java.time.LocalDate eventDate;
            try {
                eventDate = java.time.LocalDate.parse(in);
            } catch (DateTimeParseException e) {
                System.out.print("Date can't be parsed. Value set as \"null\".\n");
                eventDate = null;
            }
            System.out.print("Input min age for event:\n");
            in = Tools.input();
            Long eventMinAge = Long.valueOf(in);
            System.out.print("Input tickets count(positive integer):\n");
            in = Tools.input();
            Integer eventTicketsCount = Integer.valueOf(in);
            if (eventTicketsCount <= 0) throw new TooSmallValueException("Tickets count must be bigger than 0.\n");
            System.out.print("Input descryption of event(amount of characters is below or equal 1017):\n");
            String eventDescryption = Tools.input();
            if (eventDescryption.length() > 1017)
                throw new TooLongStringException("Event's Descryption char length can't be bigger than 1017.\n");
            Coordinates coordinates = new Coordinates(x, y);
            Event event = new Event(eventName, eventDate, eventMinAge, eventTicketsCount, eventDescryption);
            return new Ticket(name, coordinates, price, comment, type, event);
        }else {
            String in;
            String name = CommandManager.getFilescannerInput();
            if (name.equalsIgnoreCase("")) throw new NullValueException("Name can't be null.\n");
            if (name.trim().equalsIgnoreCase("")) throw new EmptyStringException("Name can't be empty.\n");
            in = CommandManager.getFilescannerInput();
            Integer x = Integer.valueOf(in);
            in = CommandManager.getFilescannerInput();
            Integer y = Integer.valueOf(in);
            in = CommandManager.getFilescannerInput();
            Long price = Long.valueOf(in);
            if (price <= 0) throw new TooSmallValueException("Price can't be negative or zero.\n");
            String comment = CommandManager.getFilescannerInput();
            if (comment.length() > 1009)
                throw new TooLongStringException("Comment's char length can't be bigger than 1009.\n");
            in = CommandManager.getFilescannerInput();
            in.trim();
            in.toUpperCase();
            TicketType type;
            try {
                type = TicketType.valueOf(in);
            } catch (IllegalArgumentException e) {
                System.out.print("Ticket Type cannot be identified as one. Value set as \"null\".\n");
                type = null;
            }
            String eventName = CommandManager.getFilescannerInput();
            if (name.equalsIgnoreCase("")) throw new NullValueException("Event Name can't be null.\n");
            if (name.trim().equalsIgnoreCase("")) throw new EmptyStringException("Event Name can't be empty.\n");
            in = CommandManager.getFilescannerInput();
            java.time.LocalDate eventDate;
            try {
                eventDate = java.time.LocalDate.parse(in);
            } catch (DateTimeParseException e) {
                System.out.print("Date can't be parsed. Value set as \"null\".\n");
                eventDate = null;
            }
            in = CommandManager.getFilescannerInput();
            Long eventMinAge = Long.valueOf(in);
            in = CommandManager.getFilescannerInput();
            Integer eventTicketsCount = Integer.valueOf(in);
            if (eventTicketsCount <= 0) throw new TooSmallValueException("Tickets count must be bigger than 0.\n");
            String eventDescryption = CommandManager.getFilescannerInput();
            if (eventDescryption.length() > 1017)
                throw new TooLongStringException("Event's Descryption char length can't be bigger than 1017.\n");
            Coordinates coordinates = new Coordinates(x, y);
            Event event = new Event(eventName, eventDate, eventMinAge, eventTicketsCount, eventDescryption);
            return new Ticket(name, coordinates, price, comment, type, event);
        }
    }
}