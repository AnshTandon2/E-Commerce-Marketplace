/*
 * Program Name
 * <p>
 * Brief description of program
 * @author Ansh Tandon, CS 180 Black
 * @version Date of Completion
 *
 */// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello and welcome!");


        boolean valid = false;
        int pg1Option = 0;
        do {
            try {
                System.out.println("Select an option:\n1. Login\n2. Create New Account");
                String firstPgOption = scan.nextLine();
                if (Integer.parseInt(firstPgOption) == 1 || Integer.parseInt(firstPgOption) == 2) {
                    valid = true;
                    pg1Option = Integer.parseInt(firstPgOption);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice");
            }
        } while (!valid);

        if (pg1Option == 1) {
            System.out.println("Please Enter your Username");
            String un = scan.nextLine();
            System.out.println("Please enter your password"); 
            String pw = scan.nextLine();
            User newUser = new User(un, "placeHolder", pw);
            if (!newUser.checkAccountExists()) {
                System.out.println("Username or password incorrect");
            }
        }
        

        

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
            System.out.println("i = " + i);
        }
    }
}