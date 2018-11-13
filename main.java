import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
///Users/johnrogermepham/IdeaProjects/Neural_networks project 2/test
public class main{
    public static void main(String[] args)throws FileNotFoundException {
        boolean done = false, save_results = false, save_model_wieghts= false, print_results= true;

        String store_patterns_path;
        String test_patterns_path;
        int input = 0;
        Pattern[] stored_patterns = null;
        Pattern[] test_patterns = null;
        User_Interface ui = new User_Interface();
        ui.weclome();
        while (!done) {
            input = ui.choices();
            if (input == 1) {
                System.out.println("Load training");
                store_patterns_path = ui.get_path_to_stored_patterns();
                stored_patterns = load_patterns(store_patterns_path);
            }

            if (input == 2) {
                System.out.println("load testing patterns");
                test_patterns_path = ui.get_path_to_test_patterns();
                test_patterns = load_patterns(test_patterns_path);
            }
            if (input == 3) {
                if (stored_patterns == null)
                    System.out.println("ERROR: SPECIFY STORED PATTERNS BEFORE TESTING");
                else if (test_patterns == null)
                    System.out.println("ERROR: SPECIFY TEST PATTERNS BEFORE TESTING");
                else if (stored_patterns[0].get_input_dimensions() != test_patterns[0].get_input_dimensions())
                    System.out.println("ERROR: DIMENSIONALITY MISMATCH BETWEEN STORED AND TEST PATTERNS");
                else
                    run_test_patterns(stored_patterns, test_patterns,false, ui);
            }
            if (input == 4){
                while (true){
                    int extras = ui.get_extra_choice();
                    if (extras == 1){
                        run_test_patterns(stored_patterns, test_patterns,true, ui);
                    }
                    if (extras == 2){
                        save_weights(ui, stored_patterns);
                    }
                    if (extras == 3)
                    {
                        break;
                    }
                }

            }

            if (input == 5) {
                System.out.println("Thank you! Hope you enjoyed my FIRST Hopfield Net");
                done = true;
            }
        }
    }
    private static void save_weights(User_Interface ui, Pattern[] stored_patterns)throws FileNotFoundException{
        String path_to_save = ui.get_path_to_save_weights();
        Model new_model = new Model(stored_patterns);
        double [][] weights =  new_model.getWeights();
        PrintWriter writer = new PrintWriter(path_to_save);
        writer.println("Model Weights--------------------------------");
        for( int i = 0; i < weights.length; i ++){
            for (int j = 0; j < weights[0].length; j++)
                writer.print(weights[i][j] + " ");
            writer.println();
        }
        writer.close();
    }
    private static void run_test_patterns(Pattern[] stored_patterns, Pattern[] testing_patterns, boolean save_results,
                                          User_Interface ui)throws FileNotFoundException{
        String path_to_save;
        Model new_model = new Model(stored_patterns);

        if (save_results) {
            path_to_save = ui.get_path_to_save_results();
            PrintWriter writer = new PrintWriter(path_to_save);

            for (int i = 0; i < stored_patterns.length; i++) {

                writer.println("\n\nTest Pattern: " + (i + 1) + " --------------------------------");
                new_model.run_pattern(testing_patterns[i]);
                double[][] export_format = testing_patterns[i].get_mapped_array();
                String[][] mapped_format = Matrix.map_to_zero_and_spaces(export_format);
                int rows = mapped_format.length;
                int columns = mapped_format[0].length;

                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < columns; k++)
                        writer.print(String.valueOf(mapped_format[j][k]) + " ");
                    writer.println();
                }
                writer.println();
            }
            writer.close();
        }
        else {
            for (int i = 0; i < stored_patterns.length; i++) {
                System.out.println("\n\nTest Pattern: " + (i + 1) + " --------------------------------");
                new_model.run_pattern(testing_patterns[i]);
                testing_patterns[i].print();
            }
        }
    }

    private static Pattern[] load_patterns(String path) throws FileNotFoundException{
        String[][][] patterns_from_file =  Input_Output.file_reader(path);
        double[][][] mapped_patterns = Matrix.map_input_patterns(patterns_from_file);
        Pattern[] patterns = new Pattern[mapped_patterns.length];

        for (int i = 0; i < mapped_patterns.length; i++) {
            patterns[i] = new Pattern(mapped_patterns[i]);
        }
        return patterns;
    }

}
