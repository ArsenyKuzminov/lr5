import java.time.format.DateTimeParseException;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.*;
import java.util.Scanner;

/**
 * class for static utility methods
 */
public class Tools {
    private static String SaveFile;
    private static boolean sf = false;
    private static String DataFile;
    private static boolean df = false;
    private static String filename;
    private static boolean chlog=false;
    private static boolean dataFileSet=false;

    /**
     * save file setter
     * prevents from changing after set
     *
     * @param saveFile
     * @throws IOException
     */
    public static void setSaveFile(String saveFile) throws IOException {
        if (!sf) {
            SaveFile=saveFile;
            File file = new File(SaveFile);
            if (!file.isFile()) {
                file.createNewFile();
                file.setWritable(true);
                sf=true;
            }
            else if (!file.canWrite()) {
                sf=true;
                file.setWritable(true);
            }
        }
         else System.out.print("Change of file refused.\n");
    }

    /**
     * sf logical getter
     *
     * @return boolean
     */
    public static boolean getSF() {
        return sf;
    }

    /**
     * data file setter
     * prevents from changing after set
     */
    public static void setDataFile(String dataFile) {
        if (!df) {
            Scanner lc = new Scanner(System.in);
            DataFile = dataFile;
            File file = new File(DataFile);
            if (file.isFile()) {
                if (file.length()!=0) {
                    file.setReadable(true);
                    dataFileSet=true;
                    df=true;
                    return;
                } else {
                    System.out.print("Your file is empty. Would you like to change it?\nIf yes, enter \"Y\"(exact as this one), in other case just enter anything (you'll start with empty collection).\n");
                    String string = lc.nextLine();
                    if (string!=null||string.equals("Y")) {
                        System.out.print("Enter the name of new file with data:\n");
                        dataFile = lc.nextLine();
                        setDataFile(dataFile);
                    } else {
                        df=true;
                        return;
                    }
                }
            } else {
                System.out.print("Your file doesn't exist or is directory. Would you like to change it?\nIf yes, enter \"Y\"(exact as this one), in other case just enter anything (you'll start with empty collection).\n");
                String string = lc.nextLine();
                if (string!=null||string.equals("Y")) {
                    System.out.print("Enter the name of new file with data:\n");
                    dataFile = lc.nextLine();
                    setDataFile(dataFile);
                } else {
                    df=true;
                    return;
                }
            }
        } else System.out.print("Change of file refused.\n");
    }

    /**
     * returns status of data file set
     * @return boolean
     */
    public static boolean getDataFileStatus(){
        return dataFileSet;
    }
    /**
     * sf logical getter
     *
     * @return boolean
     */
    public static boolean getDF() {
        return df;
    }

    /**
     * method to return elements of the collection in csv format
     *
     * @param tickets
     */
    public static String PrintTicketSet(TreeSet<Ticket> tickets) {
        String ret = new String("");
        ret = ret + "id,name,x,y,creationDate,price,comment,type,eventID,eventName,eventDate,eventMinAge,eventTicketsCount,eventDescryption\n";
        Iterator<Ticket> iter = tickets.iterator();
        Ticket ticket;
        while (iter.hasNext()) ret = ret + (ticket = iter.next()).toString();
        return ret;
    }

    /**
     * method to save data in file
     *
     * @throws IOException
     */
    public static void Save() throws IOException {
        FileOutputStream fos = new FileOutputStream(SaveFile);
        fos.write(new String("").getBytes());
        byte[] input = PrintTicketSet(TicketCollection.getTickets()).getBytes();
        fos.write(input);
        fos.close();
    }

    /**
     * method to fill collection from file
     */
    public static void ReadCSV() throws IOException {
        FileReader fr = new FileReader(DataFile);
        Scanner sc = new Scanner(fr);
        String string;
        if (sc.hasNext()) string = sc.nextLine();
        while (sc.hasNext()) {
            string = sc.nextLine();
            String[] args = string.split(",");
            String name;
            try {
                name = args[0];
                Integer x = Integer.valueOf(args[1]);
                Integer y = Integer.valueOf(args[2]);
                Long price = null;
                price = Long.valueOf(args[3]);
                String comment = null;
                if (args[4].equals("")) comment = null;
                else comment = args[4];
                TicketType ttype = null;
                if (args[5].equals("")) ttype = null;
                else ttype = TicketType.valueOf(args[5]);
                String ename = null;
                if (args[6].equals("")) ename = null;
                else ename = args[6];
                java.time.LocalDate edate;
                try {
                    edate = java.time.LocalDate.parse(args[7]);
                } catch (DateTimeParseException e) {
                    edate=null;
                }
                Long minage = Long.valueOf(args[8]);
                Integer ticketscount = null;
                if (args[9].equals("")) ticketscount = null;
                else try {
                    ticketscount = Integer.valueOf(args[9]);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                String edescryption = null;
                if (args[10].equals("")) edescryption = null;
                else edescryption = args[10];
                Coordinates coordinates = new Coordinates(x, y);
                Event event = new Event(ename, edate, minage, ticketscount, edescryption);
                Ticket ticket = new Ticket(name, coordinates, price, comment, ttype, event);
                TicketCollection.getTickets().add(ticket);
            } catch (NullValueException e){
            } catch (NumberFormatException e){
            }catch (TooSmallValueException e){
            }catch (TooBigValueException e){
            }catch (IllegalArgumentException e){
            } catch (EmptyStringException e) {
            } catch (TooLongStringException e){
        }
        }
        fr.close();
        sc.close();
    }

    /**
     * utility method for executing recursing scripts
     *
     * @param scanner
     * @param ftp
     */
    public static void skipLines(Scanner scanner, Integer ftp) {
        for (int i = 0; i < ftp; i++) {
            scanner.nextLine();
        }
    }
}