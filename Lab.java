import java.io.*;
import java.util.*;

/**
 * main class of the program
 */
public class Lab {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String args []) throws IOException {
        try{
            if(args.length>1||args.length==0){
                throw new InappropriateArgsException("This system only accept input of one file name.\n");
            }
        }catch (InappropriateArgsException e){
            System.out.print(e.getMessage());
        }
        Tools.setDataFile(args[0]);
        CommandManager commandManager=new CommandManager();
        TicketCollection.doInitialization();
        if (Tools.getDataFileStatus()) Tools.ReadCSV();
        System.out.print("You may like to use \"help\" command to see the list of possible commands.\n");
        String commandText=null;
        String line[]=null;
        while(true) {
            boolean exist=false;//make sure command exists
            AbstractCommand abstractCommand;
            try{
                if (!CommandManager.getSEIP()) System.out.print("Input your command:\n");

                if (CommandManager.getSEIP()) {
                    commandText = CommandManager.getFilescannerInput();
                    if (commandText!=null)
                        line = commandText.split(" ");
                    else {
                        CommandManager.seEnded();
                        line=Tools.input().split(" ");
                    }
                }
                else {
                    line = Tools.input().split(" ");
                }
                Iterator<AbstractCommand> iterator = commandManager.getCommands().iterator();
                while (iterator.hasNext()){
                    if((abstractCommand= iterator.next()).getName().equalsIgnoreCase(line[0])){
                        if (!(abstractCommand.getName().equalsIgnoreCase("ExecuteScript")&&CommandManager.getSEIP())) {
                            abstractCommand.execute(commandManager, line);
                            exist = true; //set true when command exists
                        } else{
                            exist = true;
                            System.out.print("\"ExecuteScript\" command don't support internal \"ExecuteScript\"'s.\nThis feature might be realised later.\n");
                        }
                    }
                }
                if(!exist){
                    throw new NoSuchCommandException("Such command doesn't exist, please enter another one.\n");
                }
            }catch (NoSuchCommandException e){
                System.out.print(e.getMessage()+"You might want to use command \"help\" to see the list of commands.\n");
            }catch (InappropriateArgsException e){
                System.out.print(e.getMessage());
                System.out.print("Input another command.\n");
            }catch (IOException e){
                System.out.print("File not found or can't be read.\n");
            }catch (NullValueException e){
                System.out.print(e.getMessage());
            }catch (NumberFormatException e){
                System.out.print("A string was entered instead of number.\n");
            }catch (TooSmallValueException e){
                System.out.print(e.getMessage());
            } catch (TooBigValueException e){
                System.out.print(e.getMessage());
            } catch (IllegalArgumentException e){
                System.out.print(e.getMessage());
            } catch (EmptyStringException e) {
                System.out.print(e.getMessage());
            } catch (TooLongStringException e){
                System.out.print(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.print("Scanner can't find new line.\nExiting the program.\n");
                System.exit(2);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.print("Event's value can't be parsed.\n");
            } catch (NullPointerException e){
                System.out.print("Something went wrong. Canceling an operation...\n");
            } catch (ConcurrentModificationException e){

            } catch (SecurityException e){
                System.out.print("Troubles with security system, so canceling command.\n");
            }
        }
    }
}