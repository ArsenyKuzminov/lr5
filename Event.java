import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * class Event
 */
public class Event {
    private Long id; //!=null, unique, auto
    private String name; //!=null, !=empty
    private java.time.LocalDate date; //!=null
    private Long minAge; //!=null
    private Integer ticketsCount; //!=null, >0
    private String descryption; //length<1017, !=null
    private static long eventID = 0;

    /**
     * constructor
     * @param name
     * @param date
     * @param minAge
     * @param ticketsCount
     * @param descryption
     */
    public Event(String name,java.time.LocalDate date, Long minAge, Integer ticketsCount, String descryption) throws NullValueException, TooBigValueException, TooLongStringException, EmptyStringException, TooSmallValueException{
        setName(name);
        setMinAge(minAge);
        setTicketsCount(ticketsCount);
        setDescryption(descryption);
        setDate(date);
        setID();
    }

    public Event(String ename, LocalDateTime edate, Long minage, Integer ticketscount, String arg) {
    }

    /**
     * id setter
     */
    public void setID() {
        eventID++;
        this.id=eventID;
    }

    /**
     * name setter. throw exception if value is inappropriate
     * @param name
     * @throws EmptyStringException, NullValueException
     */
    public void setName(String name) throws EmptyStringException, NullValueException{
        if (name==null) throw new NullValueException("Name can't be null.\n");
        else if (name.equals("")) throw new EmptyStringException("Name can't be empty.\n");
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
     * minAge setter. throw exception if value is inappropriate
     * @param minAge
     * @throws NullValueException
     */
    public void setMinAge(Long minAge) throws NullValueException{
        if (minAge==null) throw new NullValueException("Min age can't be null.\n");
        else this.minAge=minAge;
    }

    /**
     * minAge getter
     * @return Long
     */
    public Long getMinAge() {
        return minAge;
    }

    /**
     * tickets count setter. throw exception if value is inappropriate
     * @param ticketsCount
     * @throws NullValueException, TooSmallValueException
     */
    public void setTicketsCount(Integer ticketsCount) throws NullValueException, TooSmallValueException{
        if (ticketsCount==null) throw new NullValueException("Tickets count can't be null.\n");
        else if (ticketsCount<1) throw new TooSmallValueException("Tickets count can't be smaller or equal 0.\n");
        else this.ticketsCount=ticketsCount;
    }
    /**
     * ticketsCount getter
     * @return Integer
     */
    public Integer getTicketsCount() {
        return ticketsCount;
    }

    /**
     * descryption setter. throw exception if value is inappropriate
     * @param descryption
     * @throws TooLongStringException, NullValueException
     */
    public void setDescryption(String descryption) throws TooLongStringException, NullValueException{
        if (descryption==null) throw new NullValueException("Descryption can't be null.\n");
        else if (descryption.length()>1017) throw new TooLongStringException("Descryption can't be longer than 1017 symbols.\n");
        else this.descryption=descryption;
    }

    /**
     * descryption getter
     * @return String
     */
    public String getDescryption() {
        return descryption;
    }

    /**
     * date setter.
     * @param date
     */
    public void setDate(java.time.LocalDate date) {
        this.date = date;
    }

    public String getDate(){
        if (date==null) return "null";
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    /**
     * print information about class
     * @return String
     */
    @Override
    public String toString() throws NullPointerException{
        return this.id + "," + this.name + "," + getDate() + "," + this.minAge + "," + this.ticketsCount + "," + this.descryption;
    }

    /**
     * overriden hash code method
     */
    @Override
    public int hashCode() {
        return Objects.hash(id,name,date,minAge,ticketsCount,descryption);
    }

    /**
     * converts csv string to event
     */
    public static Event parse (String string) {
        String[] Array = string.split(",");
        String name=Array[0];
        java.time.LocalDate date;
        try {
            date = java.time.LocalDate.parse(Array[1]);
        }
        catch(DateTimeParseException e){
            date=null;
        }
        try {
            Long minAge = Long.valueOf(Array[2]);
            Integer ticketsCount = Integer.valueOf(Array[3]);
            String descryption = Array[4];
            return new Event(name,date,minAge,ticketsCount,descryption);
        } catch (NumberFormatException e){
            return null;
        }
    }
}