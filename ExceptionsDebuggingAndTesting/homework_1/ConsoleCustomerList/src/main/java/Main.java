import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    //add Василий Петров vasily.petrov@gmail.com +79215637722
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String THIS_IS_CONSTANT = "Command examples:\n" + COMMAND_EXAMPLES;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            try{
                String command = scanner.nextLine().trim();
                String[] tokens = command.split("\\s+", 2);
                LOGGER.info(command);

                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                    LOGGER.info("added");
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                    LOGGER.info("removed");
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(THIS_IS_CONSTANT);
                } else {
                    System.out.println(COMMAND_ERROR);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                LOGGER.error(ex.getMessage());
            }
        }
    }
}