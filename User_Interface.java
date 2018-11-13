
import java.util.Scanner;
import java.io.File;

public class User_Interface {

    public User_Interface()
    {
    }

    public void weclome(){
        System.out.println("Hi All! Welcome to my first Hopfield Net");

    }

    public int choices(){
        System.out.println("What would you like to do?");
        System.out.println("\t(1)load a training patterns.");
        System.out.println("\t(2)load a testing patterns.");
        System.out.println("\t(3)Run testing patterns.");
        System.out.println("\t(4)Extras");
        System.out.println("\t(5)Quit");
        return get_choice(5);

    }

    public int get_extra_choice(){
        System.out.println("What would you like to do?");
        System.out.println("\t(1)Run Test Patterns and save results");
        System.out.println("\t(2)Save model weights to text file");
        System.out.println("\t(3)Back to main menu");
        return get_choice(3);

    }

    public int get_choice(int max)
    {
        Scanner user_input = new Scanner(System.in);  // Reading from System.in
        int n=0;
        System.out.println("\tPlease enter your selection: ");
        try {
            int input = user_input.nextInt(); // Scans the next token of the input as an int.
            n = Integer.valueOf(input);
        }
        catch (Exception e){
            System.out.println("\tPROGRAM IS ASKING FOR AN INT!!!!");
        }
        if ((n > 0) && (n<=max)){
            return n;
        }
        else {
            System.out.println("\tERROR: SELECTION INVALID");
            n = get_choice(max);
        }
        return n;
    }

    public String get_path_to_stored_patterns()  {
        boolean valid_file= false;
        while(!valid_file) {
            System.out.println("Please enter the path to your STORED PATTERNS!");

            Scanner keyboard = new Scanner(System.in);
            String filename = keyboard.nextLine();

            try {

                File file = new File(filename);
                if (file.exists())
                    return filename;
                else
                    System.out.println("ERROR: INVALID PATH");
            } catch (Exception e) {

                System.out.println("ERROR: FILE I/O");
            }
        }
        return null;
    }

    public String get_path_to_test_patterns()  {
        boolean valid_file= false;
        while(!valid_file) {
            System.out.println("Please enter the path to your TEST PATTERNS!");

            Scanner keyboard = new Scanner(System.in);
            String filename = keyboard.nextLine();

            try {

                File file = new File(filename);
                if (file.exists())
                    return filename;
                else
                    System.out.println("ERROR: INVALID PATH");
            } catch (Exception e) {

                System.out.println("ERROR: FILE I/O");
            }
        }
        return null;
    }

    public String get_path_to_save_results(){
        System.out.println("Enter the path to save the results to!");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();
        return filename;
    }

    public String get_path_to_save_weights(){
        System.out.println("Enter the path to save the weights at.");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();

        return filename;
    }
}


